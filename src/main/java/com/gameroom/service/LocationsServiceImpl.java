package com.gameroom.service;

import com.gameroom.dto.LocationDto;
import com.gameroom.repository.LocationsRepository;
import com.gameroom.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<LocationDto> getLocations() {
        return modelConverter.convertLocationsToDtoList(locationsRepository.findAll());
    }
}
