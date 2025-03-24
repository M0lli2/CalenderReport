package com.example.calenderreport.repository;

import com.example.calenderreport.dto.CalenderRequestDto;
import com.example.calenderreport.dto.CalenderResponseDto;
import com.example.calenderreport.entity.Calender;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CalenderRepository {

    Calender saveCalender(Calender calender);

    List<CalenderResponseDto> findAllCalenders();

    Calender findCalenderById(Long id);

    void deleteCalender(Long id);
}
