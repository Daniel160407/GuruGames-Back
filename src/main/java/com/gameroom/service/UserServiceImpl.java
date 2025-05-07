package com.gameroom.service;

import com.gameroom.dto.UserDto;
import com.gameroom.model.User;
import com.gameroom.repository.UsersRepository;
import com.gameroom.service.exception.UserAlreadyRegisteredException;
import com.gameroom.service.exception.UserNotRegisteredException;
import com.gameroom.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final ModelConverter modelConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, ModelConverter modelConverter, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.modelConverter = modelConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void login(UserDto userDto) {
        Optional<User> user = usersRepository.findByEmail(userDto.getEmail());
        if (user.isPresent() && user.get().getEmail().equals(userDto.getEmail()) &&
                passwordEncoder.matches(userDto.getPassword(), user.get().getPassword())) {
        } else {
            throw new UserNotRegisteredException("User is not registered!");
        }
    }

    @Override
    public void register(UserDto userDto) {
        Optional<User> user = usersRepository.findByEmail(userDto.getEmail());
        if (user.isEmpty()) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            usersRepository.save(modelConverter.convert(userDto));
        } else {
            throw new UserAlreadyRegisteredException("User with same Email address is already registered!");
        }
    }
}
