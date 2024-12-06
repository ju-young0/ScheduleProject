package org.example.scheduleproject.service;

import org.example.scheduleproject.dto.ScheduleRequstDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;
import org.example.scheduleproject.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    
    private final ScheduleRepository scheduleRepository;
    private final JdbcTemplate jdbcTemplate;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, JdbcTemplate jdbcTemplate) {
        this.scheduleRepository = scheduleRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public ScheduleResponseDto save(ScheduleRequstDto dto) {

        Schedule schedule = new Schedule(dto);

        return scheduleRepository.save(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findingScheduleId(Long id) {

        ScheduleResponseDto schedule = scheduleRepository.findSucheduleByIdOrElseThrow(id);

        return schedule;
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String name, String contents) {

        if(name == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        int updateRow = scheduleRepository.updateSchedule(id, name, contents);

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        ScheduleResponseDto schedule = scheduleRepository.findSucheduleByIdOrElseThrow(id);

        return schedule;
    }

    @Override
    public void deleteSuchedule(Long id) {
        int deleteRow = scheduleRepository.deleteSuchedule(id);

        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }


}
