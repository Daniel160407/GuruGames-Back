package com.gameroom.service;

import com.gameroom.dto.LocationDto;
import com.gameroom.model.Location;
import com.gameroom.repository.LocationsRepository;
import com.gameroom.util.ModelConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationsServiceImpl implements LocationsService {
    private final LocationsRepository locationsRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public LocationsServiceImpl(LocationsRepository locationsRepository, ModelConverter modelConverter) {
        this.locationsRepository = locationsRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public List<LocationDto> addLocation(LocationDto locationDto) {
        locationsRepository.save(modelConverter.convert(locationDto));
        return modelConverter.convertLocationsToDtoList(locationsRepository.findAll());
    }

    @Override
    public List<Location> getLocations() {
        return locationsRepository.findAll();
    }

    @Override
    public List<Location> editLocation(Location location) {
        Optional<Location> locationOptional = locationsRepository.findById(location.getId());
        if (locationOptional.isPresent()) {
            Location editedLocation = locationOptional.get();
            editedLocation.setName(location.getName());
            editedLocation.setAddress(location.getAddress());
            editedLocation.setPhone(location.getPhone());
            editedLocation.setHours(location.getHours());
            editedLocation.setFeatures(location.getFeatures());
            editedLocation.setLat(location.getLat());
            editedLocation.setLng(location.getLng());

            locationsRepository.save(editedLocation);
        }
        return locationsRepository.findAll();
    }

    @Override
    @Transactional
    public List<Location> deleteLocation(Integer id) {
        locationsRepository.deleteById(id);
        return locationsRepository.findAll();
    }
}
