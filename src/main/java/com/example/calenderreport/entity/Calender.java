package com.example.calenderreport.entity;

import com.example.calenderreport.dto.CalenderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Calender {

    private Long id;
    private String writer;
    private String password;
    private String todo;
    private String date;

    public void update(CalenderRequestDto requestDto) {

    }
}
