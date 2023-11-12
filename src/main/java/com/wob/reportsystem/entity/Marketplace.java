package com.wob.reportsystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Marketplace {

    public Marketplace() {
    }

    public Marketplace(Integer id, String marketplaceName) {
        this.id = id;
        this.marketplaceName = marketplaceName;
    }

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "marketplace_name")
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

}
