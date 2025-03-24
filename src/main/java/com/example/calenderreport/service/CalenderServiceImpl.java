package com.example.calenderreport.service;

import com.example.calenderreport.dto.CalenderRequestDto;
import com.example.calenderreport.dto.CalenderResponseDto;
import com.example.calenderreport.entity.Calender;
import com.example.calenderreport.repository.CalenderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CalenderServiceImpl implements CalenderService{

    private final CalenderRepository calenderRepository;

    public CalenderServiceImpl(CalenderRepository calenderRepository) {
        this.calenderRepository = calenderRepository;
    }

    @Override
    public CalenderResponseDto saveCalender(CalenderRequestDto dto) {

        Calender calender = new Calender(dto.getWriter(), dto.getPassword(), dto.getTodo(), dto.getDate());
        Calender savedCalender = calenderRepository.saveCalender(calender);

        return new CalenderResponseDto(savedCalender);
    }

    @Override
    public List<CalenderResponseDto> findAllCalenders() {
        List<CalenderResponseDto> allCalenders = calenderRepository.findAllCalenders();

        return allCalenders;
    }

    @Override
    public CalenderResponseDto findCalenderById(Long id) {
        Calender calender = calenderRepository.findCalenderById(id);
        if (calender == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id를 찾을 수 없음 =" + id);
        }
        return new CalenderResponseDto(calender);
    }

    @Override
    public CalenderResponseDto updateCalender(Long id, String writer, String todo) {
        Calender calender = calenderRepository.findCalenderById(id);
        if (calender == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id를 찾을 수 없음 =" + id);
        }
        if (writer == null || todo != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title is a required value.");
        }

        return new CalenderResponseDto(calender);
    }

    @Override
    public void deleteCalender(Long id) {
        Calender calender = calenderRepository.findCalenderById(id);

        if (calender == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id를 찾을 수 없음 =" + id);
        }
        calenderRepository.deleteCalender(id);
    }


}
