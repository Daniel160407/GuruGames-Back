package com.gameroom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_consoles")
public class UserConsole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "console_id")
    private Integer consoleId;
    @Column(name = "booking_date")
    private String bookingDate;
    @Column(name = "booking_time")
    private String bookingTime;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "guests_num")
    private Integer guestsNum;
}
