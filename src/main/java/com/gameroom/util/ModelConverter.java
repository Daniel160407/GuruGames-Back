package com.gameroom.util;

import com.gameroom.dto.*;
import com.gameroom.model.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ModelConverter {
    public User convert(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .password(userDto.getPassword())
                .build();
    }

    public ConsoleDto convert(Console console) {
        if (console == null) {
            return null;
        }

        return ConsoleDto.builder()
                .id(console.getId())
                .name(console.getName())
                .description(console.getDescription())
                .state(console.getState())
                .features(convertFeaturesStringToList(console.getFeatures()))
                .price(console.getPrice())
                .userId(console.getUserId())
                .build();
    }

    public Console convert(ConsoleDto consoleDto) {
        if (consoleDto == null) {
            return null;
        }

        return Console.builder()
                .id(consoleDto.getId())
                .name(consoleDto.getName())
                .description(consoleDto.getDescription())
                .state(consoleDto.getState())
                .features(convertFeaturesListToString(consoleDto.getFeatures()))
                .price(consoleDto.getPrice())
                .userId(consoleDto.getUserId())
                .build();
    }

    public Location convert(LocationDto locationDto) {
        return Location.builder()
                .name(locationDto.getName())
                .address(locationDto.getAddress())
                .phone(locationDto.getPhone())
                .hours(locationDto.getHours())
                .features(locationDto.getFeatures())
                .lat(locationDto.getLat())
                .lng(locationDto.getLng())
                .build();
    }

    public UserConsole convert(UserConsoleDto userConsoleDto) {
        return UserConsole.builder()
                .userId(userConsoleDto.getUserId())
                .consoleId(userConsoleDto.getConsoleId())
                .locationId(userConsoleDto.getLocationId())
                .bookingDate(userConsoleDto.getBookingDate())
                .bookingTime(userConsoleDto.getBookingTime())
                .duration(userConsoleDto.getDuration())
                .guestsNum(userConsoleDto.getGuestsNum())
                .build();
    }

    public Message convert(MessageDto messageDto) {
        return Message.builder()
                .name(messageDto.getName())
                .email(messageDto.getEmail())
                .message(messageDto.getMessage())
                .build();
    }

    public List<MessageDto> convertMessagesToDtoList(List<Message> messages) {
        List<MessageDto> messageDtos = new ArrayList<>();
        messages.forEach(message -> messageDtos.add(new MessageDto(message.getName(), message.getEmail(), message.getMessage())));
        return messageDtos;
    }

    public List<ConsoleDto> convertConsolesToDtoList(List<Console> consoles) {
        if (consoles == null) {
            return Collections.emptyList();
        }

        return consoles.stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private List<String> convertFeaturesStringToList(String features) {
        if (features == null || features.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(features.split(","))
                .map(String::trim)
                .filter(feature -> !feature.isEmpty())
                .collect(Collectors.toList());
    }

    private String convertFeaturesListToString(List<String> features) {
        if (features == null || features.isEmpty()) {
            return "";
        }

        return features.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(feature -> !feature.isEmpty())
                .collect(Collectors.joining(","));
    }

    public List<LocationDto> convertLocationsToDtoList(List<Location> locations) {
        List<LocationDto> locationDtos = new ArrayList<>();
        locations.forEach(location -> locationDtos.add(new LocationDto(
                location.getName(),
                location.getAddress(),
                location.getPhone(),
                location.getHours(),
                location.getFeatures(),
                location.getLat(),
                location.getLng()
        )));
        return locationDtos;
    }
}