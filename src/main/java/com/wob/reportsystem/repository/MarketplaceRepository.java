package com.wob.reportsystem.repository;

import com.wob.reportsystem.entity.ListingStatus;
import com.wob.reportsystem.entity.Marketplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketplaceRepository extends JpaRepository<Marketplace, Integer> {
}
