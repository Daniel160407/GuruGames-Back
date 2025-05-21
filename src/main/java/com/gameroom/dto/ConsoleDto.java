package com.gameroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsoleDto {
    private Integer id;
    private String name;
    private String description;
    private Integer state;
    @Builder.Default
    private List<String> features = List.of();
    private String price;
    private Integer userId;
}