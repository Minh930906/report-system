package com.wob.reportsystem.service;

import com.wob.reportsystem.entity.ListingStatus;
import com.wob.reportsystem.exception.ListingStatusNotFound;
import com.wob.reportsystem.repository.ListingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingStatusService {

    private final ListingStatusRepository listingStatusRepository;

    @Autowired
    public ListingStatusService(ListingStatusRepository listingStatusRepository) {
        this.listingStatusRepository = listingStatusRepository;
    }

    public void save(ListingStatus listingStatus){
        listingStatusRepository.save(listingStatus);
    }

    public void saveAll(List<ListingStatus> listingStatuses){
        listingStatusRepository.saveAll(listingStatuses);
    }

    public ListingStatus findById(Integer id) {
        return listingStatusRepository.findById(id).orElseThrow(() -> new ListingStatusNotFound("listingStatus id can not be found in listing_status table: " + id));
    }

}
