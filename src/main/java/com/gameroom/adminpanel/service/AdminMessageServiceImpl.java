package com.gameroom.adminpanel.service;

import com.gameroom.dto.MessageDto;
import com.gameroom.repository.MessagesRepository;
import com.gameroom.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMessageServiceImpl implements AdminMessageService {
    private final MessagesRepository messagesRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public AdminMessageServiceImpl(MessagesRepository messagesRepository, ModelConverter modelConverter) {
        this.messagesRepository = messagesRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public List<MessageDto> getMessages() {
        return modelConverter.convertMessagesToDtoList(messagesRepository.findAll());
    }

    @Override
    public List<MessageDto> deleteMessage(Integer id) {
        messagesRepository.deleteById(id);
        return modelConverter.convertMessagesToDtoList(messagesRepository.findAll());
    }
}
