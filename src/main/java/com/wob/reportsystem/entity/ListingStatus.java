package com.wob.reportsystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class ListingStatus {

    public ListingStatus() {
    }

    public ListingStatus(Integer id, String statusName, List<Listing> listings) {
        this.id = id;
        this.statusName = statusName;
    }

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "status_name")
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

}
