package com.wob.reportsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wob.reportsystem.dto.AmazonData;
import com.wob.reportsystem.dto.EBayData;
import com.wob.reportsystem.dto.MonthlyReports;
import com.wob.reportsystem.dto.ReportDTO;
import com.wob.reportsystem.repository.ListingRepository;
import com.wob.reportsystem.service.FileUploadService;
import com.wob.reportsystem.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.Month;
import java.util.HashMap;
import java.util.List;

@RestController
public class ListingController {

    private final ListingService listingService;

    private final FileUploadService fileUploadService;

    @Autowired
    public ListingController(ListingService listingService, FileUploadService fileUploadService) {
        this.listingService = listingService;
        this.fileUploadService = fileUploadService;
    }

    @Value("${ftp.server}")
    private String ftpServer;

    @Value("${ftp.username}")
    private String ftpUsername;

    @Value("${ftp.password}")
    private String ftpPassword;

    @Value("${ftp.remoteFilePathReportJson}")
    private String remoteFilePathReportJson;

    @Value("${ftp.remoteFilePathImportLog}")
    private String remoteFilePathImportLog;

    @GetMapping("/getReports")
    public void getData() {
        ReportDTO report = new ReportDTO();
        report.setTotalListingCount(listingService.countAllListing());

        EBayData eBayData = new EBayData();
        eBayData.setTotalListingCount(listingService.countByMarketplaceId(1));
        eBayData.setTotalListingPrice(listingService.getTotalListingPriceByMarketplace(1));
        eBayData.setAverageListingPrice(listingService.avgListingPriceByMarketplaceId(1));
        report.seteBayData(eBayData);

        AmazonData amazonData = new AmazonData();
        amazonData.setTotalListingCount(listingService.countByMarketplaceId(2));
        amazonData.setTotalListingPrice(listingService.getTotalListingPriceByMarketplace(2));
        amazonData.setAverageListingPrice(listingService.avgListingPriceByMarketplaceId(2));
        report.setAmazonData(amazonData);

        report.setBestListerEmailAddress(listingService.findMostFrequentOwnerEmailAddress());


        MonthlyReports monthlyReports = new MonthlyReports();
        monthlyReports.setTotalEBayListingCountPerMonth(getListingCountPerMonthByMarketplace(1));
        monthlyReports.setTotaleBayListingPricePerMonth(totalListingPricePerMonthByMarketplace(1));
        monthlyReports.setAverageEBayListingPricePerMonth(findAvgListingPricePerMonthByMarketplace(1));
        monthlyReports.setAverageAmazonListingPricePerMonth(findAvgListingPricePerMonthByMarketplace(2));
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

    }

    @PostMapping("/upload")
    public void UploadFilesToFtp() {
        fileUploadService.uploadFileToFTP(ftpServer, ftpUsername, ftpPassword, "/Users/miki/Desktop/Projects/report-system/report.json", remoteFilePathReportJson);
        fileUploadService.uploadFileToFTP(ftpServer, ftpUsername, ftpPassword, "/Users/miki/Desktop/Projects/report-system/importLog.csv", remoteFilePathImportLog);
        System.out.println("The files have been successfully uploaded.");
    }


    private HashMap<String, String> findMostFrequentEmailAddressesPerMonth() {
        HashMap<String, String> mostFrequentEmailPerMonth = new HashMap<>();

        for (int monthNumber = 1; monthNumber <= 12; monthNumber++) {
            String mostFrequentOwnerEmailAddressPerMonth = listingService.findMostFrequentOwnerEmailAddressPerMonth(monthNumber);
            String monthName = Month.of(monthNumber).toString();
            mostFrequentEmailPerMonth.put(monthName, mostFrequentOwnerEmailAddressPerMonth);
        }

        return mostFrequentEmailPerMonth;
    }

    private HashMap<String, Long> getListingCountPerMonthByMarketplace(Integer marketplace) {
        HashMap<String, Long> ebayListingCountPerMonth = new HashMap<>();

        List<Object[]> totalEbayListingCountPerMonth = listingService.findTotalListingCountPerMonthByMarketplace(marketplace);
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
        List<Object[]> totalListingPricePerMonthByEbay = listingService.findTotalListingPricePerMonthByMarketplace(marketplace);

        for (Object[] result : totalListingPricePerMonthByEbay) {
            int month = (int) result[0];
            String monthName = Month.of(month).toString();
            double totalListingPrice = (double) result[1];
            totalListingPricePerMonth.put(monthName, totalListingPrice);
        }
        return totalListingPricePerMonth;
    }

    private HashMap<String, Double> findAvgListingPricePerMonthByMarketplace(Integer marketplace) {
        HashMap<String, Double> avgListingPricePerMonth = new HashMap<>();
        List<Object[]> totalListingPricePerMonthByEbay = listingService.findAvgListingPricePerMonthByMarketplace(marketplace);

        for (Object[] result : totalListingPricePerMonthByEbay) {
            int month = (int) result[0];
            String monthName = Month.of(month).toString();
            double totalListingPrice = (double) result[1];
            avgListingPricePerMonth.put(monthName, totalListingPrice);
        }
        return avgListingPricePerMonth;
    }
}
