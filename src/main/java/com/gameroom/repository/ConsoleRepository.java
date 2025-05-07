package com.gameroom.repository;

import com.gameroom.model.Console;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {
    List<Console> findAllByUserId(Integer userId);

    List<Console> findAllByState(Integer state);
}
