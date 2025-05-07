package com.gameroom.adminpanel.controller;

import com.gameroom.adminpanel.service.AdminConsoleService;
import com.gameroom.dto.ConsoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adminpanel/console")
@CrossOrigin(origins = "*")
public class AdminConsoleController {
    private final AdminConsoleService adminConsoleService;

    @Autowired
    public AdminConsoleController(AdminConsoleService adminConsoleService) {
        this.adminConsoleService = adminConsoleService;
    }

    @PostMapping
    public ResponseEntity<?> addConsole(@RequestBody ConsoleDto consoleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminConsoleService.addNewConsole(consoleDto));
    }

    @PutMapping
    public ResponseEntity<?> editConsole(@RequestBody List<ConsoleDto> consoleDtos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminConsoleService.editConsole(consoleDtos));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteConsole(@RequestParam Integer id) {
        return ResponseEntity.ok(adminConsoleService.deleteConsole(id));
    }
}
