package com.gameroom.service;

import com.gameroom.dto.LocationDto;
import com.gameroom.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationsService {
    List<LocationDto> addLocation(LocationDto locationDto);

    List<Location> getLocations();
    List<Location>editLocation(Location location);
    List<Location>deleteLocation(Integer id);
}
