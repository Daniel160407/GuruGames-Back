package com.gameroom.adminpanel.service;

import com.gameroom.dto.ConsoleDto;
import com.gameroom.model.Console;
import com.gameroom.repository.ConsoleRepository;
import com.gameroom.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminConsoleServiceImpl implements AdminConsoleService {
    private final ConsoleRepository consoleRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public AdminConsoleServiceImpl(ConsoleRepository consoleRepository, ModelConverter modelConverter) {
        this.consoleRepository = consoleRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public List<ConsoleDto> addNewConsole(ConsoleDto consoleDto) {
        consoleRepository.save(modelConverter.convert(consoleDto));
        return modelConverter.convertConsolesToDtoList(consoleRepository.findAll());
    }

    @Override
    public List<ConsoleDto> editConsole(List<ConsoleDto> consoleDtos) {
        ConsoleDto original = consoleDtos.get(0);
        ConsoleDto edited = consoleDtos.get(1);

        Optional<Console> consoleOptional = consoleRepository.findById(original.getId());

        if (consoleOptional.isPresent()) {
            Console console = consoleOptional.get();
            console.setName(edited.getName());
            console.setDescription(edited.getDescription());
            console.setState(edited.getState());
            console.setUserId(edited.getUserId());

            consoleRepository.save(console);
        }

        return modelConverter.convertConsolesToDtoList(consoleRepository.findAll());
    }

    @Override
    public List<ConsoleDto> deleteConsole(Integer id) {
        consoleRepository.deleteById(id);
        return modelConverter.convertConsolesToDtoList(consoleRepository.findAll());
    }
}
