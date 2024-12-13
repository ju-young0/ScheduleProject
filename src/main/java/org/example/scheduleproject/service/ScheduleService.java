package org.example.scheduleproject.service;

import org.example.scheduleproject.dto.ScheduleRequstDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto save(ScheduleRequstDto requestDto);

    List<ScheduleResponseDto> findAllSchedules(String name, String updateAt);

    ScheduleResponseDto findingScheduleId(Long id);

    ScheduleResponseDto updateSchedule(Long id, String name, String contents);

    void deleteSuchedule(Long id);
}
