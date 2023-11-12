package com.wob.reportsystem.mapper;

import com.wob.reportsystem.dto.MarketplaceDTO;
import com.wob.reportsystem.entity.Marketplace;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MarketplaceMapper {

    public Marketplace convertToEntity(MarketplaceDTO marketplaceDTO) {
        if (marketplaceDTO == null) {
            return null;
        }

        Marketplace marketplace = new Marketplace();
        marketplace.setId(marketplaceDTO.getId());
        marketplace.setMarketplaceName(marketplaceDTO.getMarketplaceName());


        return marketplace;
    }

    public List<Marketplace> convertToEntities(List<MarketplaceDTO> marketplaceDTOs) {
        return marketplaceDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public MarketplaceDTO convertToDTO(Marketplace marketplace) {
        if (marketplace == null) {
            return null;
        }

        MarketplaceDTO marketplaceDTO = new MarketplaceDTO();
        marketplaceDTO.setId(marketplace.getId());
        marketplaceDTO.setMarketplaceName(marketplace.getMarketplaceName());

        return marketplaceDTO;
    }

    public List<MarketplaceDTO> convertToDTOs(List<Marketplace> marketplaces) {
        return marketplaces.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
