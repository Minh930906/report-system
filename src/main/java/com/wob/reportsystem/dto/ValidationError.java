package com.wob.reportsystem.dto;

import java.util.UUID;

public class ValidationError {

    public ValidationError() {
    }

    public ValidationError(UUID listingId, String marketplaceName, String invalidField) {
        this.listingId = listingId;
        this.marketplaceName = marketplaceName;
        this.invalidField = invalidField;
    }
    private UUID listingId;
    private String marketplaceName;
    private String invalidField;

    public UUID getListingId() {
        return listingId;
    }

    public void setListingId(UUID listingId) {
        this.listingId = listingId;
    }

    public String getMarketplaceName() {
        return marketplaceName;
    }

    public void setMarketplaceName(String marketplaceName) {
        this.marketplaceName = marketplaceName;
    }

    public String getInvalidField() {
        return invalidField;
    }

    public void setInvalidField(String invalidField) {
        this.invalidField = invalidField;
    }
}

