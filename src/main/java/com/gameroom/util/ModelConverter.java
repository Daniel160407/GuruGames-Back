package com.gameroom.util;

import com.gameroom.dto.UserDto;
import com.gameroom.model.User;
import org.springframework.stereotype.Component;

@Component
public class ModelConverter {
    public User convert(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
