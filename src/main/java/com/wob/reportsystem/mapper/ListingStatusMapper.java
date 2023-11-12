package com.wob.reportsystem.mapper;

import com.wob.reportsystem.dto.ListingStatusDTO;
import com.wob.reportsystem.entity.ListingStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListingStatusMapper {

    public ListingStatus convertToEntity(ListingStatusDTO listingStatusDTO) {
        if (listingStatusDTO == null) {
            return null;
        }

        ListingStatus listingStatus = new ListingStatus();
        listingStatus.setId(listingStatusDTO.getId());
        listingStatus.setStatusName(listingStatusDTO.getStatusName());


        return listingStatus;
    }

    public List<ListingStatus> convertToEntities(List<ListingStatusDTO> listingStatusDTOs) {
        return listingStatusDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public ListingStatusDTO convertToDTO(ListingStatus listingStatus) {
        if (listingStatus == null) {
            return null;
        }

        ListingStatusDTO listingStatusDTO = new ListingStatusDTO();
        listingStatusDTO.setId(listingStatus.getId());
        listingStatusDTO.setStatusName(listingStatus.getStatusName());


        return listingStatusDTO;
    }

    public List<ListingStatusDTO> convertToDTOs(List<ListingStatus> listingStatuses) {
        return listingStatuses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}

