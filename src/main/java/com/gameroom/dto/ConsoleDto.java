package com.gameroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsoleDto {
    private Integer id;
    private String name;
    private String description;
    private Integer state;
    private Integer userId;

    public ConsoleDto(String name, String description, Integer state, Integer userId) {
        this.name = name;
        this.description = description;
        this.state = state;
        this.userId = userId;
    }

    public ConsoleDto(String name, String description, Integer state) {
        this.name = name;
        this.description = description;
        this.state = state;
    }
}
