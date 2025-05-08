package com.gameroom.adminpanel.service;

import com.gameroom.adminpanel.model.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminAuthorizationService {
    void login(Admin admin);
}
