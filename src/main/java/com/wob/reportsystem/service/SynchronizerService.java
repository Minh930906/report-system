package com.wob.reportsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wob.reportsystem.dto.*;
import com.wob.reportsystem.entity.Listing;
import com.wob.reportsystem.exception.ListingStatusNotFound;
import com.wob.reportsystem.exception.LocationNotFoundException;
import com.wob.reportsystem.exception.MarketplaceNotFound;
import com.wob.reportsystem.mapper.ListingMapper;
import com.wob.reportsystem.mapper.ListingStatusMapper;
import com.wob.reportsystem.mapper.LocationMapper;
import com.wob.reportsystem.mapper.MarketplaceMapper;
import com.wob.reportsystem.service.ListingService;
import com.wob.reportsystem.service.ListingStatusService;
import com.wob.reportsystem.service.LocationService;
import com.wob.reportsystem.service.MarketplaceService;
import jakarta.annotation.PostConstruct;
//import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SynchronizerService {

    private final Validator validator;
    private final ListingMapper listingMapper;
    private final ListingStatusMapper listingStatusMapper;
    private final LocationMapper locationMapper;
    private final MarketplaceMapper marketplaceMapper;
    private final ListingService listingService;
    private final ListingStatusService listingStatusService;
    private final LocationService locationService;
    private final MarketplaceService marketplaceService;

    @Autowired
    public SynchronizerService(Validator validator, ListingMapper listingMapper, ListingStatusMapper listingStatusMapper, LocationMapper locationMapper, MarketplaceMapper marketplaceMapper, ListingService listingService, ListingStatusService listingStatusService, LocationService locationService, MarketplaceService marketplaceService) {
        this.validator = validator;
        this.listingMapper = listingMapper;
        this.listingStatusMapper = listingStatusMapper;
        this.locationMapper = locationMapper;
        this.marketplaceMapper = marketplaceMapper;
        this.listingService = listingService;
        this.listingStatusService = listingStatusService;
        this.locationService = locationService;
        this.marketplaceService = marketplaceService;
    }


    @PostConstruct
    public void init() throws JsonProcessingException {
        try {
            getListingStatusDTOS();
            getMarketplaceDTOS();
            getLocationDTOS();
            getListingDTOS();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void getLocationDTOS() {
        RestTemplate restTemplate = new RestTemplate();

        String baseUrl = "https://my.api.mockaroo.com/location?key=63304c70";

        ResponseEntity<List<LocationDTO>> responseEntity = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDTO>>() {
                }
        );

        List<LocationDTO> locations = responseEntity.getBody();


        locationService.saveAll(locationMapper.convertToEntities(locations));

    }

    private void getListingStatusDTOS() {
        RestTemplate restTemplate = new RestTemplate();

        String baseUrl = "https://my.api.mockaroo.com/listingStatus?key=63304c70";

        ResponseEntity<List<ListingStatusDTO>> responseEntity = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ListingStatusDTO>>() {
                }
        );

        List<ListingStatusDTO> listingStatuses = responseEntity.getBody();
        listingStatusService.saveAll(listingStatusMapper.convertToEntities(listingStatuses));

    }

    private void getMarketplaceDTOS() {
        RestTemplate restTemplate = new RestTemplate();

        String baseUrl = "https://my.api.mockaroo.com/marketplace?key=63304c70";

        ResponseEntity<List<MarketplaceDTO>> responseEntity = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MarketplaceDTO>>() {
                }
        );

        List<MarketplaceDTO> marketplaces = responseEntity.getBody();

        marketplaceService.saveAll(marketplaceMapper.convertToEntities(marketplaces));
    }

    private void getListingDTOS() {
        RestTemplate restTemplate = new RestTemplate();

        String baseUrl = "https://my.api.mockaroo.com/listing?key=63304c70";

        ResponseEntity<List<ListingDTO>> responseEntity = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ListingDTO>>() {
                }
        );

        List<ValidationError> validationErrors = new ArrayList<>();
        List<Listing> validListings = new ArrayList<>();
        List<ListingDTO> listings = responseEntity.getBody();
        for (ListingDTO listing : listings) {
            Errors errors = new BeanPropertyBindingResult(listing, "listing");
            validator.validate(listing, errors);

            if (errors.hasErrors()) {
                ValidationError validationError = createValidationError(listing, errors);
                validationErrors.add(validationError);
            } else {
                try {
                    validListings.add(listingMapper.convertToEntity(listing));
                } catch (LocationNotFoundException | ListingStatusNotFound | MarketplaceNotFound exception) {
                    ValidationError validationError = createValidationError(listing, exception);
                    validationErrors.add(validationError);
                }
            }
        }
        writeValidationErrorToCsv(validationErrors);
        listingService.saveAll(validListings);
    }

    private ValidationError createValidationError(ListingDTO listing, Errors errors) {
        ValidationError validationError = new ValidationError();
        validationError.setListingId(listing.getId());
        validationError.setMarketplaceName(marketplaceService.findById(listing.getMarketplace()).getMarketplaceName());
        validationError.setInvalidField(errors.getFieldErrors().get(0).getField());
        return validationError;
    }

    private ValidationError createValidationError(ListingDTO listing, Exception exception) {
        ValidationError validationError = new ValidationError();
        validationError.setListingId(listing.getId());
        validationError.setMarketplaceName(marketplaceService.findById(listing.getMarketplace()).getMarketplaceName());
        if (exception instanceof LocationNotFoundException) {
            validationError.setInvalidField("locationId");
        } else if (exception instanceof ListingStatusNotFound) {
            validationError.setInvalidField("listingStatus");
        } else if (exception instanceof MarketplaceNotFound) {
            validationError.setInvalidField("marketplace");
        }
        return validationError;
    }


    private void writeValidationErrorToCsv(List<ValidationError> validationErrors) {
        try (PrintWriter writer = new PrintWriter("importLog.csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ListingId");
            sb.append(';');
            sb.append("MarketplaceName");
            sb.append(';');
            sb.append("InvalidField");
            sb.append('\n');

            for (ValidationError error : validationErrors) {
                sb.append(error.getListingId());
                sb.append(';');
                sb.append(error.getMarketplaceName());
                sb.append(';');
                if (error.getInvalidField() == null) {
                    System.out.println(error.getInvalidField());
                }
                sb.append(error.getInvalidField());
                sb.append('\n');
            }

            writer.write(sb.toString());

            System.out.println("Errors written to importLog.csv");
        } catch (IOException e) {
            System.err.println("Error writing to importLog.csv: " + e.getMessage());
        }
    }
}
