package com.wob.reportsystem.dto;

import java.util.Map;

public class MonthlyReports {
    public MonthlyReports() {
    }

    public MonthlyReports(Map<String, Long> totalEBayListingCountPerMonth, Map<String, Double> totalEBayListingPricePerMonth, Map<String, Double> averageEBayListingPricePerMonth, Map<String, Double> averageAmazonListingPricePerMonth, Map<String, Long> totalAmazonListingCountPerMonth, Map<String, Double> totalAmazonListingPricePerMonth, Map<String,String> bestListerEmailAddressOfMonth) {
        this.totalEBayListingCountPerMonth = totalEBayListingCountPerMonth;
        this.totalEBayListingPricePerMonth = totalEBayListingPricePerMonth;
        this.averageEBayListingPricePerMonth = averageEBayListingPricePerMonth;
        this.averageAmazonListingPricePerMonth = averageAmazonListingPricePerMonth;
        this.totalAmazonListingCountPerMonth = totalAmazonListingCountPerMonth;
        this.totalAmazonListingPricePerMonth = totalAmazonListingPricePerMonth;
        this.bestListerEmailAddressOfMonth = bestListerEmailAddressOfMonth;
    }

    private Map<String,Long> totalEBayListingCountPerMonth;
    private Map<String,Double> totalEBayListingPricePerMonth;
    private Map<String,Double> averageEBayListingPricePerMonth;
    private Map<String,Double> averageAmazonListingPricePerMonth;
    private Map<String,Long> totalAmazonListingCountPerMonth;
    private Map<String,Double> totalAmazonListingPricePerMonth;
    private Map<String,String> bestListerEmailAddressOfMonth;

    public Map<String, Long> getTotalEBayListingCountPerMonth() {
        return totalEBayListingCountPerMonth;
    }

    public void setTotalEBayListingCountPerMonth(Map<String, Long> totalEBayListingCountPerMonth) {
        this.totalEBayListingCountPerMonth = totalEBayListingCountPerMonth;
    }

    public Map<String, Double> getTotaleBayListingPricePerMonth() {
        return totalEBayListingPricePerMonth;
    }

    public void setTotaleBayListingPricePerMonth(Map<String, Double> totaleBayListingPricePerMonth) {
        this.totalEBayListingPricePerMonth = totaleBayListingPricePerMonth;
    }

    public Map<String, Double> getAverageeBayListingPricePerMonth() {
        return averageEBayListingPricePerMonth;
    }

    public void setAverageEBayListingPricePerMonth(Map<String, Double> averageEBayListingPricePerMonth) {
        this.averageEBayListingPricePerMonth = averageEBayListingPricePerMonth;
    }

    public Map<String, Double> getAverageAmazonListingPricePerMonth() {
        return averageAmazonListingPricePerMonth;
    }

    public void setAverageAmazonListingPricePerMonth(Map<String, Double> averageAmazonListingPricePerMonth) {
        this.averageAmazonListingPricePerMonth = averageAmazonListingPricePerMonth;
    }

    public Map<String, Long> getTotalAmazonListingCountPerMonth() {
        return totalAmazonListingCountPerMonth;
    }

    public void setTotalAmazonListingCountPerMonth(Map<String, Long> totalAmazonListingCountPerMonth) {
        this.totalAmazonListingCountPerMonth = totalAmazonListingCountPerMonth;
    }

    public Map<String, Double> getTotalAmazonListingPricePerMonth() {
        return totalAmazonListingPricePerMonth;
    }

    public void setTotalAmazonListingPricePerMonth(Map<String, Double> totalAmazonListingPricePerMonth) {
        this.totalAmazonListingPricePerMonth = totalAmazonListingPricePerMonth;
    }

    public Map<String,String> getBestListerEmailAddressOfMonth() {
        return bestListerEmailAddressOfMonth;
    }

    public void setBestListerEmailAddressOfMonth(Map<String,String> bestListerEmailAddressOfMonth) {
        this.bestListerEmailAddressOfMonth = bestListerEmailAddressOfMonth;
    }
}
