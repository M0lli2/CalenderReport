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
import java.util.Optional;

@Service
public class CalenderServiceImpl implements CalenderService{

    private final CalenderRepository calenderRepository;

    public CalenderServiceImpl(CalenderRepository calenderRepository) {
        this.calenderRepository = calenderRepository;
    }

    @Override
    public CalenderResponseDto saveCalender(CalenderRequestDto dto) {

        Calender calender = new Calender(dto.getWriter(), dto.getPassword(), dto.getTodo(), dto.getDate());

        return calenderRepository.saveCalender(calender);
    }

    @Override
    public List<CalenderResponseDto> findAllCalenders() {
        List<CalenderResponseDto> allCalenders = calenderRepository.findAllCalenders();

        return allCalenders;
    }

    @Override
    public CalenderResponseDto findCalenderById(Long id) {
        Optional<Calender> optionalCalender = calenderRepository.findCalenderById(id);
        if (optionalCalender.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id를 찾을 수 없음 =" + id);
        }
        return new CalenderResponseDto(optionalCalender.get());
    }

    @Override
    public CalenderResponseDto updateCalender(Long id, String writer, String date) {

        if (writer == null || date != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title is a required values.");
        }

        int updateRow = calenderRepository.updateDate(id, writer, date);
        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        return new CalenderResponseDto(calenderRepository.findCalenderById(id).get());
    }

    @Override
    public void deleteCalender(Long id) {

        int deletedRow = calenderRepository.deleteCalender(id);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id를 찾을 수 없음 =" + id);
        }

    }


}
