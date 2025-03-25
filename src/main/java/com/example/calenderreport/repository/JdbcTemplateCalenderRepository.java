package com.example.calenderreport.repository;

import com.example.calenderreport.dto.CalenderResponseDto;
import com.example.calenderreport.entity.Calender;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateCalenderRepository implements CalenderRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCalenderRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public CalenderResponseDto saveCalender(Calender calender) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("calender").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("password", calender.getPassword());
        parameters.put("todo", calender.getTodo());
        parameters.put("writer", calender.getWriter());
        parameters.put("create_date", calender.getCreated_date());
        parameters.put("update_date", calender.getUpdated_date());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CalenderResponseDto(key.longValue(), calender.getPassword(), calender.getTodo(), calender.getWriter(), calender.getCreated_date(), calender.getUpdated_date());
    }


    @Override
    public List<CalenderResponseDto> findAllCalenders(String date, String writer) {

        return jdbcTemplate.query("select * from schedule.schedule where substr(updated_date, 1,7) = ? OR writer = ? order by updated_date desc", calenderRowMapper(), date, writer);
    }

    private RowMapper<CalenderResponseDto> calenderRowMapper() {
        return new RowMapper<CalenderResponseDto>() {
            @Override
            public CalenderResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CalenderResponseDto(
                        rs.getLong("id"),
                        rs.getString("password"),
                        rs.getString("todo"),
                        rs.getString("writer"),
                        rs.getString("update_date"),
                        rs.getString("created_date")
                );
            }
        };
    }

    @Override
    public Optional<Calender> findCalenderById(Long id) {
        List<Calender> result = jdbcTemplate.query("select * from schedule_data where id = ?", calenderRowMapperV2(), id);


        return result.stream().findAny();
    }

    private RowMapper<Calender> calenderRowMapperV2() {
        return new RowMapper<Calender>() {
            @Override
            public Calender mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Calender(
                        rs.getLong("id"),
                        rs.getString("password"),
                        rs.getString("todo"),
                        rs.getString("writer"),
                        rs.getString("update_date"),
                        rs.getString("created_date")
                );
            }
        };
    }

    @Override
    public int updateDate(Long id, String writer, String todo) {
        return jdbcTemplate.update("update schedule set writer = ? where id = ?", writer, todo, id);
    }

    @Override
    public int deleteCalender(Long id) {
        return 0;
    }


    @Override
    public int deleteCalender(Long id, String password) {
        return jdbcTemplate.update("delete from schedule where password = ? and id = ?", password, id);
    }


}
