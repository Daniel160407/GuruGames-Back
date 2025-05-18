package com.gameroom.adminpanel.controller;

import com.gameroom.dto.LocationDto;
import com.gameroom.service.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminpanel/locations")
public class AdminLocationsController {
    private final LocationsService locationsService;

    @Autowired
    public AdminLocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @PostMapping
    public ResponseEntity<?> addLocation(@RequestBody LocationDto locationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationsService.addLocation(locationDto));
    }
}
