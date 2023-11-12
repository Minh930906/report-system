package com.wob.reportsystem.repository;

import com.wob.reportsystem.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, UUID> {

    @Query("SELECT COUNT(l) FROM Listing l")
    Long countAllListings();

    @Query("SELECT COUNT(l) FROM Listing l WHERE l.marketplace.id = :marketplaceId")
    Long countByMarketplaceId(Integer marketplaceId);

    @Query("SELECT AVG(l.listingPrice) FROM Listing l WHERE l.marketplace.id = :marketplaceId")
    Double avgListingPriceByMarketplaceId(@Param("marketplaceId") Integer marketplaceId);

    @Query("SELECT SUM(l.listingPrice) FROM Listing l WHERE l.marketplace.id = :marketplaceId")
    Double getTotalListingPriceByMarketplace(@Param("marketplaceId") Integer marketplaceId);

    @Query("SELECT l.ownerEmailAddress FROM Listing l GROUP BY l.ownerEmailAddress ORDER BY COUNT(l.ownerEmailAddress) DESC limit 1")
    String findMostFrequentOwnerEmailAddress();

    @Query("SELECT MONTH(l.uploadTime) AS month, COUNT(l) AS listingCount FROM Listing l WHERE l.marketplace.id = :marketplaceId GROUP BY MONTH(l.uploadTime)")
    List<Object[]> findTotalListingCountPerMonthByMarketplace(@Param("marketplaceId") Integer marketplaceId);

    @Query("SELECT MONTH(l.uploadTime), SUM(l.listingPrice) FROM Listing l WHERE l.marketplace.id = :marketplaceId GROUP BY MONTH(l.uploadTime)")
    List<Object[]> findTotalListingPricePerMonthByMarketplace(@Param("marketplaceId") Integer marketplaceId);

    @Query("SELECT MONTH(l.uploadTime), AVG(l.listingPrice) FROM Listing l WHERE l.marketplace.id = :marketplaceId GROUP BY MONTH(l.uploadTime)")
    List<Object[]> findAvgListingPricePerMonthByMarketplace(@Param("marketplaceId") Integer marketplaceId);

    @Query("SELECT l.ownerEmailAddress " +
            "FROM Listing l " +
            "WHERE MONTH(l.uploadTime) = :month " +
            "GROUP BY l.ownerEmailAddress " +
            "ORDER BY COUNT(l.ownerEmailAddress) DESC " +
            "LIMIT 1")
    String findMostFrequentOwnerEmailAddressPerMonth(@Param("month") int month);



}
