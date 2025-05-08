package com.gameroom.service;

import com.gameroom.dto.UserDto;
import com.gameroom.model.CreditCard;
import com.gameroom.model.User;
import com.gameroom.repository.UsersRepository;
import com.gameroom.service.exception.adminpanel.InvalidEmailOrPasswordException;
import com.gameroom.service.exception.user.MissingRequiredDataException;
import com.gameroom.service.exception.user.UserAlreadyRegisteredException;
import com.gameroom.service.exception.user.UserNotRegisteredException;
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
    public CreditCard login(UserDto userDto) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,}$";

        if (!userDto.getPassword().matches(regex)) {
            throw new IllegalArgumentException(
                    "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character (!@#$%^&*()_+=-)."
            );
        }

        Optional<User> userOptional = usersRepository.findByPhoneNumber(userDto.getPhoneNumber());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getName().equals(userDto.getName()) &&
                    user.getLastname().equals(userDto.getLastname()) &&
                    passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {

                return CreditCard.builder()
                        .number(user.getCardNumber())
                        .expiry(user.getExpiry())
                        .securityCode(user.getSecurityCode())
                        .build();
            } else {
                throw new InvalidEmailOrPasswordException("Invalid email or password!");
            }
        } else {
            throw new UserNotRegisteredException("User is not registered!");
        }
    }


    @Override
    public void register(UserDto userDto) {
        Optional<User> user;
        if (userDto.getPhoneNumber() != null && !userDto.getPhoneNumber().equals("")) {
            user = usersRepository.findByPhoneNumber(userDto.getPhoneNumber());
        } else if (userDto.getEmail() != null) {
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!userDto.getEmail().matches(emailRegex)) {
                throw new IllegalArgumentException("Invalid email format!");
            }

            user = usersRepository.findByEmail(userDto.getEmail());
        } else {
            throw new MissingRequiredDataException("You must enter email or phone number!");
        }

        if (user.isEmpty()) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            usersRepository.save(modelConverter.convert(userDto));
        } else {
            throw new UserAlreadyRegisteredException("User with same Email address or Phone number is already registered!");
        }
    }

}
