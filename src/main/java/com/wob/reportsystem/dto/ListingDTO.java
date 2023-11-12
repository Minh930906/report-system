package com.wob.reportsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wob.reportsystem.validator.FieldsNotNull;
import com.wob.reportsystem.validator.PositiveDecimal;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.UUID;

@FieldsNotNull(fields = {"id",
        "title",
        "description",
        "locationId",
        "listingPrice",
        "currency",
        "quantity",
        "listingStatus",
        "marketplace",
        "uploadTime",
        "ownerEmailAddress"})
public class ListingDTO {

    public ListingDTO() {
    }

    public ListingDTO(UUID id, String title, String description, UUID locationId, Double listingPrice, String currency, Integer quantity, Integer listingStatus, Integer marketplace, Date uploadTime, String ownerEmailAddress) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.locationId = locationId;
        this.listingPrice = listingPrice;
        this.currency = currency;
        this.quantity = quantity;
        this.listingStatus = listingStatus;
        this.marketplace = marketplace;
        this.uploadTime = uploadTime;
        this.ownerEmailAddress = ownerEmailAddress;
    }

    private UUID id;
    private String title;
    private String description;
    @JsonProperty("location_id")
    private UUID locationId;
    @JsonProperty("listing_price")
    @PositiveDecimal
    private Double listingPrice;
    @Size(min = 3, max = 3, message = "Currency code must be exactly 3 characters")
    private String currency;
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;
    @JsonProperty("listing_status")
    private Integer listingStatus;
    private Integer marketplace;
    @JsonProperty("upload_time")
    @JsonFormat(pattern = "M/d/yyyy")
    @DateTimeFormat(pattern = "M/d/yyyy")
    @NotNull
    private Date uploadTime;
    @JsonProperty("owner_email_address")
    @Email
    private String ownerEmailAddress;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }

    public Double getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(double listingPrice) {
        this.listingPrice = listingPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(int listingStatus) {
        this.listingStatus = listingStatus;
    }

    public Integer getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(int marketplace) {
        this.marketplace = marketplace;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getOwnerEmailAddress() {
        return ownerEmailAddress;
    }

    public void setOwnerEmailAddress(String ownerEmailAddress) {
        this.ownerEmailAddress = ownerEmailAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("description", description)
                .append("locationId", locationId)
                .append("listingPrice", listingPrice)
                .append("currency", currency)
                .append("quantity", quantity)
                .append("listingStatus", listingStatus)
                .append("marketplace", marketplace)
                .append("uploadTime", uploadTime)
                .append("ownerEmailAddress", ownerEmailAddress)
                .toString();
    }
}

