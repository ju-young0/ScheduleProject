package org.example.scheduleproject.repository;

import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcTemplateRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto save(Schedule schedule) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", schedule.getName());
        parameters.put("contents", schedule.getContents());
        parameters.put("password", schedule.getPassword());
        parameters.put("createdAt", LocalDateTime.now());
        parameters.put("updatedAt", LocalDateTime.now());


        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        LocalDateTime date = (LocalDateTime) parameters.get("createdAt");
        return new ScheduleResponseDto(key.longValue(), schedule.getName(), schedule.getContents(),date,date);
    }


    @Override
    public List<ScheduleResponseDto> findAllSchedules(String name, String updateAt) {
        String sql = "select * from schedule where 1 = 1 ";
        List<Object> param = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            sql+= "and name = ? ";
            param.add(name);
        }
        if (updateAt != null && !updateAt.isEmpty()) {
            sql+= "and date(updatedAt) = ? ";
            param.add(updateAt);
        }
        sql+= "order by updatedAt desc";

        return jdbcTemplate.query(sql,param.toArray(), scheduleRowMapper());
    }

    public Optional<ScheduleResponseDto> findingScheduleId(Long id) {
        List<ScheduleResponseDto> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper(),id);
        return result.stream().findAny();
    }

    @Override
    public ScheduleResponseDto findSucheduleByIdOrElseThrow(Long id) {

        List<ScheduleResponseDto> result = jdbcTemplate.query("select * from schedule where id = ? ", scheduleRowMapper(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id));
    }

    @Override
    public int updateSchedule(Long id, String name, String contents) {
        return jdbcTemplate.update("update schedule set name = ?, contents =? , updatedAt = now() where id = ?", name,contents, id);
    }

    @Override
    public int deleteSuchedule(Long id) {
        return jdbcTemplate.update("delete from schedule where id = ?", id);
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime()
                );
            }
        };
    }


}
