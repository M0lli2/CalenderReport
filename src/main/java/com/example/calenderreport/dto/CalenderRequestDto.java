package com.example.calenderreport.dto;

import com.example.calenderreport.entity.Calender;
import lombok.Getter;

@Getter
public class CalenderRequestDto {

    private String writer;
    private String password;
    private String todo;
    private String date;


    public CalenderRequestDto(Calender calender) {
    }
}
