package org.example.scheduleproject.repository;

import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto save (Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules(String name, String updateAt);

    Optional<ScheduleResponseDto> findingScheduleId (Long id);

    ScheduleResponseDto findSucheduleByIdOrElseThrow(Long id);

   int updateSchedule(Long id, String name, String contents);

   int deleteSuchedule(Long id);

}
