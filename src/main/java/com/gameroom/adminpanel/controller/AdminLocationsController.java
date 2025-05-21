package com.gameroom.adminpanel.controller;

import com.gameroom.dto.LocationDto;
import com.gameroom.model.Location;
import com.gameroom.service.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adminpanel/locations")
@CrossOrigin(origins = "*")
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

    @PutMapping
    public ResponseEntity<?> editLocation(@RequestBody Location location) {
        return ResponseEntity.ok(locationsService.editLocation(location));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLocation(@RequestParam Integer id) {
        return ResponseEntity.ok(locationsService.deleteLocation(id));
    }
}
