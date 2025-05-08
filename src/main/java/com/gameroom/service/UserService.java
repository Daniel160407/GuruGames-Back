package com.gameroom.service;

import com.gameroom.dto.UserDto;
import com.gameroom.model.CreditCard;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    CreditCard login(UserDto userDto);

    void register(UserDto userDto);
}
