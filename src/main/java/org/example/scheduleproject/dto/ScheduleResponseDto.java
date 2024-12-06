package org.example.scheduleproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduleproject.entity.Schedule;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String name;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
