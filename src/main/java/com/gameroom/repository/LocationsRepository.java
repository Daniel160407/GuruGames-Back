package com.gameroom.repository;

import com.gameroom.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationsRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findByName(String name);
}
