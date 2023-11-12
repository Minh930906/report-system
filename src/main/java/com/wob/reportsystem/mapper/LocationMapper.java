package com.wob.reportsystem.mapper;

import com.wob.reportsystem.dto.LocationDTO;
import com.wob.reportsystem.entity.Location;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class LocationMapper {

    public Location convertToEntity(LocationDTO locationDTO) {
        if (locationDTO == null) {
            return null;
        }

        Location location = new Location();
        location.setId(locationDTO.getId());
        location.setManagerName(locationDTO.getManagerName());
        location.setPhone(locationDTO.getPhone());
        location.setAddressPrimary(locationDTO.getAddressPrimary());
        location.setAddressSecondary(locationDTO.getAddressSecondary());
        location.setCountry(locationDTO.getCountry());
        location.setTown(locationDTO.getTown());
        location.setPostalCode(locationDTO.getPostalCode());

        return location;
    }

    public List<Location> convertToEntities(List<LocationDTO> locationDTOs) {
        return locationDTOs.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public LocationDTO convertToDTO(Location location) {
        if (location == null) {
            return null;
        }

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setManagerName(location.getManagerName());
        locationDTO.setPhone(location.getPhone());
        locationDTO.setAddressPrimary(location.getAddressPrimary());
        locationDTO.setAddressSecondary(location.getAddressSecondary());
        locationDTO.setCountry(location.getCountry());
        locationDTO.setTown(location.getTown());
        locationDTO.setPostalCode(location.getPostalCode());


        return locationDTO;
    }

    public List<LocationDTO> convertToDTOs(List<Location> locations) {
        return locations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}

