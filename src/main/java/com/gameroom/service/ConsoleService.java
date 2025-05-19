package com.gameroom.service;

import com.gameroom.dto.ConsoleDto;
import com.gameroom.dto.UserConsoleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsoleService {
    List<ConsoleDto> getConsoles();

    List<ConsoleDto> getFreeConsoles();

    List<ConsoleDto> getUserConsoles(Integer userId);

    void bookConsole(UserConsoleDto userConsoleDto);
}
