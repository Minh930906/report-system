package com.wob.reportsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

public class MarketplaceDTO {

    public MarketplaceDTO() {
    }

    public MarketplaceDTO(Integer id, String marketplaceName) {
        this.id = id;
        this.marketplaceName = marketplaceName;
    }

    private Integer id;
    @JsonProperty("marketplace_name")
    private String marketplaceName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarketplaceName() {
        return marketplaceName;
    }

    public void setMarketplaceName(String marketplaceName) {
        this.marketplaceName = marketplaceName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("marketplaceName", marketplaceName)
                .toString();
    }
}
