package com.maison.demo.services;

import com.maison.demo.models.Holiday;
import com.maison.demo.repositories.ContactRepository;
import com.maison.demo.repositories.HolidayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    public HolidayService() {
        log.info("Holiday Service Bean initialized");
    }

    public List<Holiday> searchAllHolidays() {
        Iterable<Holiday> holidays = holidayRepository.findAll();
        return StreamSupport.stream(holidays.spliterator(), false).toList();
    }
}
