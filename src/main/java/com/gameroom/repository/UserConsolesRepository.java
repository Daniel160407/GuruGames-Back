package com.gameroom.repository;

import com.gameroom.model.UserConsole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConsolesRepository extends JpaRepository<UserConsole, Integer> {
}
