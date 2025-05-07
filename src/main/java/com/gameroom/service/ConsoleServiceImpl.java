package com.gameroom.service;

import com.gameroom.dto.ConsoleDto;
import com.gameroom.model.Console;
import com.gameroom.repository.ConsoleRepository;
import com.gameroom.service.exception.console.ConsoleIsBusyException;
import com.gameroom.service.exception.console.NoSuchConsoleException;
import com.gameroom.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsoleServiceImpl implements ConsoleService {
    private final ConsoleRepository consoleRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public ConsoleServiceImpl(ConsoleRepository consoleRepository, ModelConverter modelConverter) {
        this.consoleRepository = consoleRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public List<ConsoleDto> getConsoles() {
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
    public void bookConsole(Integer id, Integer userId) {
        Optional<Console> consoleOptional = consoleRepository.findById(id);
        if (consoleOptional.isPresent()) {
            Console console = consoleOptional.get();
            if (console.getState() == 0 && console.getUserId() == null) {
                console.setUserId(userId);
                console.setState(1);
                consoleRepository.save(console);
            } else {
                throw new ConsoleIsBusyException("This console is already booked by another person");
            }
        } else {
            throw new NoSuchConsoleException("There is not a console with provided id!");
        }
    }
}
