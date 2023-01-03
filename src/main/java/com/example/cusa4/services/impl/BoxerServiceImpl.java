package com.example.cusa4.services.impl;

import com.example.cusa4.models.*;
import com.example.cusa4.services.BoxerService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoxerServiceImpl implements BoxerService {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Boxer> boxerRowMapper;
    private final RowMapper<Fight> fightRowMapper;
    private final RowMapper<Title> titleRowMapper;
    private final RowMapper<Coach> coachRowMapper;

    @Override
    public List<Boxer> getBoxers() {
        return jdbcTemplate.query(
                "select * from boxer",
                boxerRowMapper
        );
    }

    @Override
    public Boxer getBoxer(int serviceNumber) throws WrongBoxerIdException {
        List<Boxer> boxerList = jdbcTemplate.query(
                "select * from boxer where serviceNumber = ?",
                boxerRowMapper,
                serviceNumber
        );

        if (boxerList.size() == 0) {
            throw new WrongBoxerIdException();
        }
        return boxerList.get(0);
    }

    @Override
    public List<Fight> getFights(int serviceNumber) {
        return jdbcTemplate.query(
                "call getBoxerFights(?)",
                fightRowMapper,
                serviceNumber
        );
    }

    @Override
    public List<Coach> getCoaches(int serviceNumber) {
        return jdbcTemplate.query(
                "call getBoxerCoaches(?)",
                coachRowMapper,
                serviceNumber
        );
    }

    @Override
    public List<Title> getTitles(int serviceNumber) {
        return jdbcTemplate.query(
                "call getBoxerTitels(?)",
                titleRowMapper,
                serviceNumber
        );
    }

    @Override
    public List<Boxer> getRespectedBoxers() {
        return jdbcTemplate.query(
                "select * from respectedBoxers",
                boxerRowMapper
        );
    }
}
