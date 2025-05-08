package com.gameroom.util;

import com.gameroom.dto.ConsoleDto;
import com.gameroom.dto.UserDto;
import com.gameroom.model.Console;
import com.gameroom.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelConverter {
    public User convert(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .password(userDto.getPassword())
                .cardNumber(userDto.getCardNumber())
                .expiry(userDto.getExpiry())
                .securityCode(userDto.getSecurityCode())
                .build();
    }

    public ConsoleDto convert(Console console) {
        return ConsoleDto.builder()
                .id(console.getId())
                .name(console.getName())
                .description(console.getDescription())
                .state(console.getState())
                .userId(console.getUserId())
                .build();
    }

    public Console convert(ConsoleDto consoleDto) {
        return Console.builder()
                .id(consoleDto.getId())
                .name(consoleDto.getName())
                .description(consoleDto.getDescription())
                .state(consoleDto.getState())
                .userId(consoleDto.getUserId())
                .build();
    }

    public List<ConsoleDto> convertConsolesToDtoList(List<Console> consoles) {
        List<ConsoleDto> consoleDtos = new ArrayList<>();
        consoles.forEach(console -> consoleDtos.add(new ConsoleDto(
                console.getId(),
                console.getName(),
                console.getDescription(),
                console.getState(),
                console.getUserId()
        )));
        return consoleDtos;
    }
}
