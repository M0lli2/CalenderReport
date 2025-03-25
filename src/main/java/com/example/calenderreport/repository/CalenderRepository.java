package com.example.calenderreport.repository;

import com.example.calenderreport.dto.CalenderRequestDto;
import com.example.calenderreport.dto.CalenderResponseDto;
import com.example.calenderreport.entity.Calender;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CalenderRepository {

    CalenderResponseDto saveCalender(Calender calender);

    List<CalenderResponseDto> findAllCalenders();

    List<CalenderResponseDto> findAllCalenders(String writer);

    List<CalenderResponseDto> findAllCalenders(String date, String writer);

    Optional<Calender> findCalenderById(Long id);

    int updateDate(Long id, String writer, String date);

    int deleteCalender(Long id);

    int deleteCalender(Long id, String password);
}
