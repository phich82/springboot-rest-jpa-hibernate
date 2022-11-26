package com.maison.demo.repositories;

import com.maison.demo.models.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
public class HolidayRepository2 {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HolidayRepository2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> searchAllHolidays() {
        String sql = "SELECT * FROM holidays";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Holiday.class));
    }

}
