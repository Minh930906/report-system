package com.wob.reportsystem.mapper;

import com.wob.reportsystem.dto.ListingDTO;
import com.wob.reportsystem.entity.Listing;
import com.wob.reportsystem.service.ListingStatusService;
import com.wob.reportsystem.service.LocationService;
import com.wob.reportsystem.service.MarketplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListingMapper {

    private final LocationService locationService;
    private final ListingStatusService listingStatusService;
    private final MarketplaceService marketplaceService;

    @Autowired
    public ListingMapper(LocationService locationService, ListingStatusService listingStatusService, MarketplaceService marketplaceService) {
        this.locationService = locationService;
        this.listingStatusService = listingStatusService;
        this.marketplaceService = marketplaceService;
    }

    public Listing convertToEntity(ListingDTO listingDTO) {
        if (listingDTO == null) {
            return null;
        }

        Listing listing = new Listing();
        listing.setId(listingDTO.getId());
        listing.setTitle(listingDTO.getTitle());
        listing.setDescription(listingDTO.getDescription());
        listing.setLocation(locationService.findById(listingDTO.getLocationId()));
        listing.setListingPrice(listingDTO.getListingPrice());
        listing.setCurrency(listingDTO.getCurrency());
        listing.setQuantity(listingDTO.getQuantity());
        listing.setListingStatus(listingStatusService.findById(listingDTO.getListingStatus()));
        listing.setMarketplace(marketplaceService.findById(listingDTO.getMarketplace()));
        listing.setUploadTime(listingDTO.getUploadTime());
        listing.setOwnerEmailAddress(listingDTO.getOwnerEmailAddress());

        return listing;
    }

    public List<Listing> convertToEntities(List<ListingDTO> listingDTOs) {
        return listingDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public ListingDTO convertToDTO(Listing listing) {
        if (listing == null) {
            return null;
        }

        ListingDTO listingDTO = new ListingDTO();
        listingDTO.setId(listing.getId());
        listingDTO.setTitle(listing.getTitle());
        listingDTO.setDescription(listing.getDescription());
        listingDTO.setLocationId(listing.getLocation().getId());
        listingDTO.setListingPrice(listing.getListingPrice());
        listingDTO.setCurrency(listing.getCurrency());
        listingDTO.setQuantity(listing.getQuantity());
        listingDTO.setListingStatus(listing.getListingStatus().getId());
        listingDTO.setMarketplace(listing.getMarketplace().getId());
        listingDTO.setUploadTime(listing.getUploadTime());
        listingDTO.setOwnerEmailAddress(listing.getOwnerEmailAddress());

        return listingDTO;
    }

    public List<ListingDTO> convertToDTOs(List<Listing> listings) {
        return listings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}

