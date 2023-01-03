package com.example.cusa4.services.impl;

import com.example.cusa4.exceptions.WrongOlympiadIdException;
import com.example.cusa4.models.*;
import com.example.cusa4.services.OlympiadService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OlympiadServiceImpl implements OlympiadService {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Boxer> boxerRowMapper;
    private final RowMapper<OlympiadDto> olympiadDtoRowMapper;

    @Override
    public List<Boxer> getCurrentOlympiadCrew(String pole) {
        return jdbcTemplate.query(
                "call getCurrentOlympiadCrew(?);",
                boxerRowMapper,
                pole
        );
    }

    @Override
    public List<OlympiadDto> getAllOlympiad()  {
        return jdbcTemplate.query(
                "select * from olympiad order by olympiadYear desc",
                olympiadDtoRowMapper
        );
    }

    @Override
    public List<Boxer> getOlympiadCrew(int olympiadId) throws WrongOlympiadIdException {
        List<Boxer> boxers = jdbcTemplate.query(
                "call getOlympiadCrew(?)",
                boxerRowMapper,
                olympiadId
        );

        if (boxers.size() == 0) {
            throw new WrongOlympiadIdException();
        }
        return boxers;
    }
}
