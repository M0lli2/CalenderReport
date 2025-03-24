package com.example.calenderreport.controller;

import com.example.calenderreport.dto.CalenderRequestDto;
import com.example.calenderreport.dto.CalenderResponseDto;
import com.example.calenderreport.entity.Calender;
import com.example.calenderreport.service.CalenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/calenders")
public class CalenderController {

    private final CalenderService calenderService;

    public CalenderController(CalenderService calenderService) {
        this.calenderService = calenderService;
    }

    // 일정 추가
    @PostMapping
    public ResponseEntity<CalenderResponseDto> createCalender(@RequestBody CalenderRequestDto dto) {

        return new ResponseEntity<>(calenderService.saveCalender(dto), HttpStatus.CREATED);
    }

    // 일정 목록 조회
    @GetMapping
    public List<CalenderResponseDto> findAllCalenders() {

        return calenderService.findAllCalenders();
    }

    // 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<CalenderResponseDto> findCalenderById(@PathVariable Long id) {

        return new ResponseEntity<>(calenderService.findCalenderById(id), HttpStatus.OK);
    }
    // 일정 전체 수정
    @PutMapping("/{id}")
    public ResponseEntity<CalenderResponseDto> updateCalender(
            @PathVariable Long id,
            @RequestBody CalenderRequestDto dto
    ) {
       return new ResponseEntity<>(calenderService.updateCalender(id, dto.getWriter(), dto.getTodo()), HttpStatus.OK);

    }
    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalender(
            @PathVariable Long id
    ) {
        calenderService.deleteCalender(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
