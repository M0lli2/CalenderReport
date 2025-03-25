package com.example.calenderreport.dto;

import com.example.calenderreport.entity.Calender;
import lombok.Getter;


@Getter
public class CalenderResponseDto {

    private Long id;
    private String writer;
    private String todo;
    private String date;

    // Calender class를 인자로 가지는 생성자
    public CalenderResponseDto(Calender calender) {
        this.id = calender.getId();
        this.writer = calender.getWriter();
        this.todo = calender.getTodo();
        this.date = calender.getDate();
    }

    public CalenderResponseDto(long id, String todo, String writer, String date) {
    }

    public CalenderResponseDto(long l, Long id, String password, String todo, String writer, String date) {
    }
}
