package com.gameroom.controller;

import com.gameroom.dto.UserConsoleDto;
import com.gameroom.service.ConsoleService;
import com.gameroom.service.exception.console.ConsoleIsBusyException;
import com.gameroom.service.exception.console.NoSuchConsoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gameroom/console")
@CrossOrigin(origins = "*")
public class ConsoleController {
    private final ConsoleService consoleService;

    @Autowired
    public ConsoleController(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @GetMapping
    public ResponseEntity<?> getConsoles() {
        return ResponseEntity.ok(consoleService.getConsoles());
    }

    @GetMapping("/free")
    public ResponseEntity<?> getFreeConsoles() {
        return ResponseEntity.ok(consoleService.getFreeConsoles());
    }

    @GetMapping("/byid")
    public ResponseEntity<?> getUserConsoles(@RequestParam Integer userid) {
        return ResponseEntity.ok(consoleService.getUserConsoles(userid));
    }

    @PutMapping
    public ResponseEntity<?> bookConsole(@RequestBody UserConsoleDto userConsoleDto) {
        try {
            consoleService.bookConsole(userConsoleDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ConsoleIsBusyException e) {
            return ResponseEntity.status(HttpStatus.LOCKED).body(e.getMessage());
        } catch (NoSuchConsoleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
