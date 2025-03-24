package com.example.calenderreport.dto;

import com.example.calenderreport.entity.Calender;
import lombok.Getter;

@Getter
public class CalenderRequestDto {

    private final Calender calender;
    private String writer;
    private String password;
    private String todo;
    private String date;


    public CalenderRequestDto(Calender calender, String writer, String password, String todo, String date) {
        this.calender = calender;
        this.writer = writer;
        this.password = password;
        this.todo = todo;
        this.date = date;
    }
}
