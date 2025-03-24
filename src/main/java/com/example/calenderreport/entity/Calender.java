package com.example.calenderreport.entity;

import com.example.calenderreport.dto.CalenderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Calender {

    private Long id;
    private String writer;
    private String password;
    private String todo;
    private String date;
    private Long calenderId;

    public Calender(String writer, String password, String todo, String date) {
        this.writer = writer;
        this.password = password;
        this.todo = todo;
        this.date = date;
    }


    public void update(CalenderRequestDto dto) {
        this.todo = dto.getTodo();
        this.writer = dto.getWriter();

    }

}
