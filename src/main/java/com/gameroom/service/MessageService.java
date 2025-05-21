package com.gameroom.service;

import com.gameroom.dto.MessageDto;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    void sendMessage(MessageDto messageDto);
}
