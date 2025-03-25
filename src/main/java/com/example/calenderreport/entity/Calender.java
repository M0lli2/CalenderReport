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
    private String created_date;
    private String updated_date;

    public Calender(String writer, String password, String todo, String created_date, String updated_date) {
        this.writer = writer;
        this.password = password;
        this.todo = todo;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

    public Calender(long id, String writer, String todo, String date) {
    }


    public void update(CalenderRequestDto dto) {
        this.todo = dto.getTodo();
        this.writer = dto.getWriter();

    }

}
