package com.wob.reportsystem.service;

import com.wob.reportsystem.entity.Listing;
import com.wob.reportsystem.entity.Marketplace;
import com.wob.reportsystem.exception.MarketplaceNotFound;
import com.wob.reportsystem.repository.MarketplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketplaceService {

    private final MarketplaceRepository marketplaceRepository;

    @Autowired
    public MarketplaceService(MarketplaceRepository marketplaceRepository) {
        this.marketplaceRepository = marketplaceRepository;
    }

    public void save(Marketplace marketplace){
        marketplaceRepository.save(marketplace);
    }
    public void saveAll(List<Marketplace> marketplaces){
        marketplaceRepository.saveAll(marketplaces);
    }
    public Marketplace findById(Integer id) {
        return marketplaceRepository.findById(id).orElseThrow(() -> new MarketplaceNotFound("Marketplace id can not be found in marketplace table: " + id));
    }
}
