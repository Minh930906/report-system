package com.wob.reportsystem.service;

import com.wob.reportsystem.entity.Listing;
import com.wob.reportsystem.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {


    private final ListingRepository listingRepository;

    @Autowired
    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public void saveAll(List<Listing> listings) {
        listingRepository.saveAll(listings);
    }

    public Long countAllListing(){
        return listingRepository.countAllListings();
    }

    public Long countByMarketplaceId(Integer marketplaceId){
        return listingRepository.countByMarketplaceId(marketplaceId);
    }

    public Double avgListingPriceByMarketplaceId(Integer marketplaceId){
        return listingRepository.avgListingPriceByMarketplaceId(marketplaceId);
    }

    public Double getTotalListingPriceByMarketplace(Integer marketplace){
        return listingRepository.getTotalListingPriceByMarketplace(marketplace);
    }

    public String findMostFrequentOwnerEmailAddress(){
        return listingRepository.findMostFrequentOwnerEmailAddress();
    }

    public List<Object[]> findTotalListingCountPerMonthByMarketplace(Integer marketplaceId){
        return listingRepository.findTotalListingCountPerMonthByMarketplace(marketplaceId);
    }

    public List<Object[]> findTotalListingPricePerMonthByMarketplace(Integer marketplaceId){
        return listingRepository.findTotalListingPricePerMonthByMarketplace(marketplaceId);
    }

    public List<Object[]> findAvgListingPricePerMonthByMarketplace(Integer marketplaceId){
        return listingRepository.findAvgListingPricePerMonthByMarketplace(marketplaceId);
    }

    public String findMostFrequentOwnerEmailAddressPerMonth(Integer month){
        return listingRepository.findMostFrequentOwnerEmailAddressPerMonth(month);
    }
}
