package com.example.calenderreport.service;

import com.example.calenderreport.dto.CalenderRequestDto;
import com.example.calenderreport.dto.CalenderResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CalenderService {
    CalenderResponseDto saveCalender(CalenderRequestDto requestDto);
    List<CalenderResponseDto> findAllCalenders();

    CalenderResponseDto findCalenderById(Long id);

    CalenderResponseDto updateCalender(Long id, String writer, String todo);

    void deleteCalender(Long id);
}
