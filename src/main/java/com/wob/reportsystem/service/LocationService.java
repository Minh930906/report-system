package com.wob.reportsystem.service;

import com.wob.reportsystem.entity.Location;
import com.wob.reportsystem.exception.LocationNotFoundException;
import com.wob.reportsystem.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void save(Location location){
        locationRepository.save(location);
    }

    public void saveAll(List<Location> locations){
        locationRepository.saveAll(locations);
    }
    public Location findById(UUID id) {
        return locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException("Location id can not be found in Location table: " + id));
    }
}
