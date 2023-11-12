package com.wob.reportsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

public class ListingStatusDTO {

    public ListingStatusDTO() {
    }

    public ListingStatusDTO(Integer id,String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    private Integer id;
    @JsonProperty("status_name")
    private String statusName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("statusName", statusName)
                .toString();
    }
}
