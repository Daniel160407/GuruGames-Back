package com.gameroom.service;

import com.gameroom.dto.MessageDto;
import com.gameroom.repository.MessagesRepository;
import com.gameroom.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessagesRepository messagesRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public MessageServiceImpl(MessagesRepository messagesRepository, ModelConverter modelConverter) {
        this.messagesRepository = messagesRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public void sendMessage(MessageDto messageDto) {
        String formattedDateTime = LocalDate.now() + " | " + LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        messageDto.setDate(formattedDateTime);
        messagesRepository.save(modelConverter.convert(messageDto));
    }
}
