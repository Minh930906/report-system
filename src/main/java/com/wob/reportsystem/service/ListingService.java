package com.wob.reportsystem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wob.reportsystem.dto.AmazonData;
import com.wob.reportsystem.dto.EBayData;
import com.wob.reportsystem.dto.MonthlyReports;
import com.wob.reportsystem.dto.ReportDTO;
import com.wob.reportsystem.entity.Listing;
import com.wob.reportsystem.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.Month;
import java.util.HashMap;
import java.util.List;

@Service
public class ListingService {


    private final ListingRepository listingRepository;

    @Autowired
    public ListingService(ListingRepository listingRepository,FileUploadService fileUploadService) {
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

    public ReportDTO getReports(){
        ReportDTO report = new ReportDTO();
        report.setTotalListingCount(listingRepository.countAllListings());

        EBayData eBayData = new EBayData();
        eBayData.setTotalListingCount(listingRepository.countByMarketplaceId(1));
        eBayData.setTotalListingPrice(listingRepository.getTotalListingPriceByMarketplace(1));
        eBayData.setAverageListingPrice(listingRepository.avgListingPriceByMarketplaceId(1));
        report.seteBayData(eBayData);

        AmazonData amazonData = new AmazonData();
        amazonData.setTotalListingCount(listingRepository.countByMarketplaceId(2));
        amazonData.setTotalListingPrice(listingRepository.getTotalListingPriceByMarketplace(2));
        amazonData.setAverageListingPrice(listingRepository.avgListingPriceByMarketplaceId(2));
        report.setAmazonData(amazonData);

        report.setBestListerEmailAddress(listingRepository.findMostFrequentOwnerEmailAddress());


        MonthlyReports monthlyReports = new MonthlyReports();
        monthlyReports.setTotalEBayListingCountPerMonth(getListingCountPerMonthByMarketplace(1));
        monthlyReports.setTotaleBayListingPricePerMonth(totalListingPricePerMonthByMarketplace(1));
        monthlyReports.setAverageEBayListingPricePerMonth(findAverageListingPricePerMonthByMarketplace(1));
        monthlyReports.setAverageAmazonListingPricePerMonth(findAverageListingPricePerMonthByMarketplace(2));
        monthlyReports.setTotalAmazonListingPricePerMonth(totalListingPricePerMonthByMarketplace(2));
        monthlyReports.setTotalAmazonListingCountPerMonth(getListingCountPerMonthByMarketplace(1));
        monthlyReports.setBestListerEmailAddressOfMonth(findMostFrequentEmailAddressesPerMonth());

        report.setMonthlyReports(monthlyReports);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("report.json"), report);
            System.out.println("Reports exported to JSON.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return report;
    }

    private HashMap<String, String> findMostFrequentEmailAddressesPerMonth() {
        HashMap<String, String> mostFrequentEmailPerMonth = new HashMap<>();

        for (int monthNumber = 1; monthNumber <= 12; monthNumber++) {
            String mostFrequentOwnerEmailAddressPerMonth = listingRepository.findMostFrequentOwnerEmailAddressPerMonth(monthNumber);
            String monthName = Month.of(monthNumber).toString();
            mostFrequentEmailPerMonth.put(monthName, mostFrequentOwnerEmailAddressPerMonth);
        }

        return mostFrequentEmailPerMonth;
    }

    private HashMap<String, Long> getListingCountPerMonthByMarketplace(Integer marketplace) {
        HashMap<String, Long> ebayListingCountPerMonth = new HashMap<>();

        List<Object[]> totalEbayListingCountPerMonth = listingRepository.findTotalListingCountPerMonthByMarketplace(marketplace);
        for (Object[] row : totalEbayListingCountPerMonth) {
            int month = (int) row[0];
            String monthName = Month.of(month).toString();
            long listingCount = (long) row[1];
            ebayListingCountPerMonth.put(monthName, listingCount);
        }
        return ebayListingCountPerMonth;
    }

    private HashMap<String, Double> totalListingPricePerMonthByMarketplace(Integer marketplace) {
        HashMap<String, Double> totalListingPricePerMonth = new HashMap<>();
        List<Object[]> totalListingPricePerMonthByEbay = listingRepository.findTotalListingPricePerMonthByMarketplace(marketplace);

        for (Object[] result : totalListingPricePerMonthByEbay) {
            int month = (int) result[0];
            String monthName = Month.of(month).toString();
            double totalListingPrice = (double) result[1];
            totalListingPricePerMonth.put(monthName, totalListingPrice);
        }
        return totalListingPricePerMonth;
    }

    private HashMap<String, Double> findAverageListingPricePerMonthByMarketplace(Integer marketplace) {
        HashMap<String, Double> avgListingPricePerMonth = new HashMap<>();
        List<Object[]> totalListingPricePerMonthByEbay = listingRepository.findAvgListingPricePerMonthByMarketplace(marketplace);

        for (Object[] result : totalListingPricePerMonthByEbay) {
            int month = (int) result[0];
            String monthName = Month.of(month).toString();
            double totalListingPrice = (double) result[1];
            avgListingPricePerMonth.put(monthName, totalListingPrice);
        }
        return avgListingPricePerMonth;
    }
}
