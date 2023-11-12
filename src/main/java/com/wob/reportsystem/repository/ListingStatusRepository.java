package com.wob.reportsystem.repository;

import com.wob.reportsystem.entity.Listing;
import com.wob.reportsystem.entity.ListingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ListingStatusRepository extends JpaRepository<ListingStatus, Integer> {
}
