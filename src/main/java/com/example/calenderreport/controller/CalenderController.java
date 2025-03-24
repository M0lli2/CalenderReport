package com.example.calenderreport.controller;

import com.example.calenderreport.dto.CalenderRequestDto;
import com.example.calenderreport.dto.CalenderResponseDto;
import com.example.calenderreport.entity.Calender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/calenders")
public class CalenderController {

    private final Map<Long, Calender>calenderList = new HashMap<>();

    // 일정 추가
    @PostMapping
    public ResponseEntity<CalenderResponseDto> createCalender(@RequestBody CalenderRequestDto dto) {

        // 식별자가 1씩 증가
        Long calenderId = calenderList.isEmpty() ? 1 : Collections.max(calenderList.keySet()) + 1;

        // 요청받은 데이터로 Calender 객체 생성
        Calender calender = new Calender(calenderId, dto.getWriter(), dto.getPassword(), dto.getTodo(), dto.getDate());

        // Inmemory DB에 Calender 메모
        calenderList.put(calenderId, calender);

        return new ResponseEntity<>(new CalenderResponseDto(calender), HttpStatus.CREATED);
    }

    // 일정 목록 조회
    @GetMapping
    public List<ResponseEntity<CalenderRequestDto>> findAllMemos() {
        List<ResponseEntity<CalenderRequestDto>> responseList = new ArrayList<>();

        for (Calender calender : calenderList.values()) {
            CalenderRequestDto dto = new CalenderRequestDto(calender);
            responseList.add(new ResponseEntity<>(dto, HttpStatus.OK));
        }

        return responseList;
    }

    // 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<CalenderResponseDto> findCalenderById(@PathVariable Long id) {

        Calender calender = calenderList.get(id);

        if (calender == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CalenderResponseDto(calender), HttpStatus.OK);
    }
    // 일정 전체 수정
    @PutMapping("/{id}")
    public ResponseEntity<CalenderResponseDto> updateCalender(
            @PathVariable Long id,
            @RequestBody CalenderRequestDto requestDto
    ) {
        Calender calender = calenderList.get(id);

        if (calender == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (requestDto.getWriter() == null || requestDto.getTodo() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        calender.update(requestDto);
        return  new ResponseEntity<>(new CalenderResponseDto(calender), HttpStatus.OK);

    }
    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalender(
            @PathVariable Long id
    ) {
        if (calenderList.containsKey(id)) {
            calenderList.remove(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
