package com.example.calenderreport.dto;

import com.example.calenderreport.entity.Calender;
import lombok.Getter;


@Getter
public class CalenderResponseDto {

    private Long id;
    private String writer;
    private String todo;
    private String created_date;
    private String updated_date;
    private String password;

    // Calender class를 인자로 가지는 생성자
    public CalenderResponseDto(Calender calender) {
        this.id = calender.getId();
        this.writer = calender.getWriter();
        this.todo = calender.getTodo();
        this.created_date = calender.getCreated_date();
        this.updated_date = calender.getUpdated_date();
    }


    public CalenderResponseDto(long id, String password, String todo, String writer, String createdDate, String updatedDate) {

    }
}
