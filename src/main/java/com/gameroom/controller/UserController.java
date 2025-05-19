package com.gameroom.controller;

import com.gameroom.dto.UserDto;
import com.gameroom.service.UserService;
import com.gameroom.service.exception.user.MissingRequiredDataException;
import com.gameroom.service.exception.user.UserAlreadyRegisteredException;
import com.gameroom.service.exception.user.UserNotRegisteredException;
import com.gameroom.util.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gameroom/user")
@CrossOrigin(origins = "*", exposedHeaders = "Authorization")
public class UserController {
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PutMapping
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletResponse response) {
        try {
            Integer userId = userService.login(userDto);

            val token = jwtUtils.generateToken(userDto.getPhoneNumber());
            response.addHeader(jwtUtils.JWT_HEADER, jwtUtils.JWT_PREFIX + token);

            return ResponseEntity.accepted().body(userId);
        } catch (UserNotRegisteredException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        try {
            userService.register(userDto);
            return ResponseEntity.accepted().build();
        } catch (UserAlreadyRegisteredException | MissingRequiredDataException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
