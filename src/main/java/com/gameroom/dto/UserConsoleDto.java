package com.gameroom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserConsoleDto {
    private Integer userId;
    private Integer consoleId;
    private String bookingDate;
    private String bookingTime;
    private Integer duration;
    private Integer guestsNum;
}
