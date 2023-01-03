package com.example.cusa4.services.impl;

import com.example.cusa4.models.*;
import com.example.cusa4.services.ProfBoxService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfBoxServiceImpl implements ProfBoxService {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<ProfBoxEvent> profBoxEventRowMapper;
    private final RowMapper<FightInFightNight> fightInFightNightRowMapper;
    private final RowMapper<Judge> judgeRowMapper;


    @Override
    public List<ProfBoxEvent> getPastEvents() {
        return jdbcTemplate.query(
                "call getLastFightNights(?)",
                profBoxEventRowMapper,
                new Date()
        );
    }

    @Override
    public List<ProfBoxEvent> getFutureEvents() {
        return jdbcTemplate.query(
                "call getFutureFightNights(?)",
                profBoxEventRowMapper,
                new Date()
        );
    }

    @Override
    public List<FightInFightNight> getFights(int fightNightId) {
        return  jdbcTemplate.query(
                "call getFights(?)",
                fightInFightNightRowMapper,
                fightNightId
        );
    }

    @Override
    public List<Judge> getJudges(int fightNightId) {
        List<Judge> judges = jdbcTemplate.query(
                "call getJudges(?)",
                judgeRowMapper,
                fightNightId
        );
        return judges;
    }

    @Override
    public List<ProfBoxEvent> getFights(String dateString) {
        return jdbcTemplate.query(
                "call getFightNightsByDate(?)",
                profBoxEventRowMapper,
                dateString
        );
    }

    @Override
    public List<ProfBoxEvent> getAllFightNights() {
        return jdbcTemplate.query(
                "select * from fightnight order by fightDate desc",
                profBoxEventRowMapper
        );
    }

    @Override
    public void deleteFightNight(int fightNightId) {
        jdbcTemplate.update(
                "delete from fightnight where fightNight.fightNumber = ?",
                fightNightId
        );
    }

}
