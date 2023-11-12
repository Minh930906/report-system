package com.wob.reportsystem.dto;

public class ReportDTO {
    public ReportDTO() {
    }

    public ReportDTO(Long totalListingCount, EBayData eBayData, AmazonData amazonData, String bestListerEmailAddress, MonthlyReports monthlyReports) {
        this.totalListingCount = totalListingCount;
        this.eBayData = eBayData;
        this.amazonData = amazonData;
        this.bestListerEmailAddress = bestListerEmailAddress;
        this.monthlyReports = monthlyReports;
    }

    private Long totalListingCount;
    private EBayData eBayData;
    private AmazonData amazonData;
    private String bestListerEmailAddress;
    private MonthlyReports monthlyReports;

    public Long getTotalListingCount() {
        return totalListingCount;
    }

    public void setTotalListingCount(Long totalListingCount) {
        this.totalListingCount = totalListingCount;
    }

    public EBayData geteBayData() {
        return eBayData;
    }

    public void seteBayData(EBayData eBayData) {
        this.eBayData = eBayData;
    }

    public AmazonData getAmazonData() {
        return amazonData;
    }

    public void setAmazonData(AmazonData amazonData) {
        this.amazonData = amazonData;
    }

    public String getBestListerEmailAddress() {
        return bestListerEmailAddress;
    }

    public void setBestListerEmailAddress(String bestListerEmailAddress) {
        this.bestListerEmailAddress = bestListerEmailAddress;
    }

    public MonthlyReports getMonthlyReports() {
        return monthlyReports;
    }

    public void setMonthlyReports(MonthlyReports monthlyReports) {
        this.monthlyReports = monthlyReports;
    }
}
