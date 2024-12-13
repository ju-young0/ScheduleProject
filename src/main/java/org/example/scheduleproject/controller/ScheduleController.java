package org.example.scheduleproject.controller;

import org.example.scheduleproject.dto.ScheduleRequstDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSuhedule(@RequestBody ScheduleRequstDto dto) {


        return new ResponseEntity<>(scheduleService.save(dto), HttpStatus.OK);
    }

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules(@RequestParam(required = false) String name, @RequestParam(required = false) String updateAt) {

        return scheduleService.findAllSchedules(name,updateAt);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findingScheduleId(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findingScheduleId(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequstDto dto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto.getName(), dto.getContents()), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSuchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}

