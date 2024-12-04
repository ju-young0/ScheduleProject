package org.example.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String name;
    private String password;
    private String contents;
    private LocalDateTime createdn;
    private LocalDateTime update;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreatedn() {
        return createdn;
    }

    public LocalDateTime getUpdate() {
        return update;
    }
}
