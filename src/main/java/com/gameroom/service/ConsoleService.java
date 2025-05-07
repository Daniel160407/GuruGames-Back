package com.gameroom.service;

import com.gameroom.dto.ConsoleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsoleService {
    List<ConsoleDto> getConsoles();

    List<ConsoleDto> getFreeConsoles();

    List<ConsoleDto> getUserConsoles(Integer userId);

    void bookConsole(Integer id, Integer userId);
}
