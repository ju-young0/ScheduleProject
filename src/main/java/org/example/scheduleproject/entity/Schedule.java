package org.example.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduleproject.dto.ScheduleRequstDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;    // id
    private String name;    // 작성자
    private String password;      // 비밀번호
    private String contents;    // 할일(내용)
    private LocalDateTime createdAt;    // 날짜등록
    private LocalDateTime updateAt;     //수정날짜

    public Schedule(ScheduleRequstDto dto) {
        this.name = dto.getName();
        this.contents = dto.getContents();
        this.password = dto.getPassword();
    }

}
