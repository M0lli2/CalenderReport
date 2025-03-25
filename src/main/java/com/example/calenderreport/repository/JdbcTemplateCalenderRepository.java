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
        parameters.put("id", calender.getId());
        parameters.put("password", calender.getPassword());
        parameters.put("todo", calender.getTodo());
        parameters.put("writer", calender.getWriter());
        parameters.put("date", calender.getDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CalenderResponseDto(key.longValue(), calender.getId(), calender.getPassword(), calender.getTodo(), calender.getWriter(), calender.getDate());
    }

    @Override
    public List<CalenderResponseDto> findAllCalenders() {
        return jdbcTemplate.query("select * from calender order by date desc", calenderRowMapper());
    }

    private RowMapper<CalenderResponseDto> calenderRowMapper() {
        return new RowMapper<CalenderResponseDto>() {
            @Override
            public CalenderResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CalenderResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("writer"),
                        rs.getString("date")
                );
            }
        };
    }

    @Override
    public Optional<Calender> findCalenderById(Long id) {
        List<Calender> result = jdbcTemplate.query("select * from calender where id = ?", calenderRowMapperV2(), id);


        return result.stream().findAny();
    }

    private RowMapper<Calender> calenderRowMapperV2() {
        return new RowMapper<Calender>() {
            @Override
            public Calender mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Calender(
                        rs.getLong("id"),
                        rs.getString("writer"),
                        rs.getString("todo"),
                        rs.getString("date")
                );
            }
        };
    }
    @Override
    public int updateDate(Long id, String writer, String date) {
        return jdbcTemplate.update("update calender set writer = ? where id = ?", writer, id);
    }



    @Override
    public int deleteCalender(Long id) {
        return jdbcTemplate.update("delete memo where id = ?", id);
    }


}
