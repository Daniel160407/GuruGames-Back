package com.gameroom.service;

import com.gameroom.dto.ConsoleDto;
import com.gameroom.dto.UserConsoleDto;
import com.gameroom.model.Console;
import com.gameroom.model.UserConsole;
import com.gameroom.repository.ConsoleRepository;
import com.gameroom.repository.UserConsolesRepository;
import com.gameroom.service.exception.console.ConsoleIsBusyException;
import com.gameroom.service.exception.console.NoSuchConsoleException;
import com.gameroom.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ConsoleServiceImpl implements ConsoleService {
    private final ConsoleRepository consoleRepository;
    private final UserConsolesRepository userConsolesRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public ConsoleServiceImpl(ConsoleRepository consoleRepository, UserConsolesRepository userConsolesRepository, ModelConverter modelConverter) {
        this.consoleRepository = consoleRepository;
        this.userConsolesRepository = userConsolesRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public List<ConsoleDto> getConsoles() {
        List<UserConsole> userConsoles = userConsolesRepository.findAll();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        for (UserConsole userConsole : userConsoles) {
            try {
                String dateStr = userConsole.getBookingDate();
                String timeStr = userConsole.getBookingTime();

                if (dateStr != null && timeStr != null) {
                    LocalDate date = LocalDate.parse(dateStr, dateFormatter);
                    LocalTime time = LocalTime.parse(timeStr, timeFormatter);
                    LocalDateTime bookingDateTime = LocalDateTime.of(date, time);

                    int duration = userConsole.getDuration();
                    LocalDateTime expirationDateTime = bookingDateTime.plusMinutes(duration);

                    if (expirationDateTime.isBefore(LocalDateTime.now())) {
                        Optional<Console> optionalConsole = consoleRepository.findById(userConsole.getConsoleId());
                        if (optionalConsole.isPresent()) {
                            Console console = optionalConsole.get();
                            console.setUserId(null);
                            console.setState(0);
                            consoleRepository.save(console);
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        }

        return modelConverter.convertConsolesToDtoList(consoleRepository.findAll());
    }


    @Override
    public List<ConsoleDto> getFreeConsoles() {
        return modelConverter.convertConsolesToDtoList(consoleRepository.findAllByState(0));
    }

    @Override
    public List<ConsoleDto> getUserConsoles(Integer userId) {
        return modelConverter.convertConsolesToDtoList(consoleRepository.findAllByUserId(userId));
    }

    @Override
    public void bookConsole(UserConsoleDto userConsoleDto) {
        Optional<Console> consoleOptional = consoleRepository.findById(userConsoleDto.getConsoleId());
        if (consoleOptional.isPresent()) {
            Console console = consoleOptional.get();
            if (console.getState() == 0 && console.getUserId() == null) {
                console.setUserId(userConsoleDto.getUserId());
                console.setState(1);
                consoleRepository.save(console);
                userConsolesRepository.save(modelConverter.convert(userConsoleDto));
            } else {
                throw new ConsoleIsBusyException("This console is already booked by another person");
            }
        } else {
            throw new NoSuchConsoleException("There is not a console with provided id!");
        }
    }
}
