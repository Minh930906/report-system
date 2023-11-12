package com.wob.reportsystem.dto;

public class AmazonData {

    public AmazonData() {
    }

    public AmazonData(Long totalListingCount, Double totalListingPrice, Double averageListingPrice) {
        this.totalListingCount = totalListingCount;
        this.totalListingPrice = totalListingPrice;
        this.averageListingPrice = averageListingPrice;
    }

    private Long totalListingCount;
    private Double totalListingPrice;
    private Double averageListingPrice;

    public Long getTotalListingCount() {
        return totalListingCount;
    }

    public void setTotalListingCount(Long totalListingCount) {
        this.totalListingCount = totalListingCount;
    }

    public Double getTotalListingPrice() {
        return totalListingPrice;
    }

    public void setTotalListingPrice(Double totalListingPrice) {
        this.totalListingPrice = totalListingPrice;
    }

    public Double getAverageListingPrice() {
        return averageListingPrice;
    }

    public void setAverageListingPrice(Double averageListingPrice) {
        this.averageListingPrice = averageListingPrice;
    }
}
