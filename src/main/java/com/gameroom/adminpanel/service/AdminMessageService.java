package com.gameroom.adminpanel.service;

import com.gameroom.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminMessageService {
    List<MessageDto> getMessages();
}
