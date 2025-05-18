package com.gameroom.service;

import com.gameroom.dto.LocationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationsService {
    List<LocationDto> addLocation(LocationDto locationDto);

    List<LocationDto> getLocations();
}
