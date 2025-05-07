package com.gameroom.adminpanel.service;

import com.gameroom.dto.ConsoleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminConsoleService {
    List<ConsoleDto> addNewConsole(ConsoleDto consoleDto);

    List<ConsoleDto> editConsole(List<ConsoleDto> consoleDtos);

    List<ConsoleDto> deleteConsole(Integer id);
}
