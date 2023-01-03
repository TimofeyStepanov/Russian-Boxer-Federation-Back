package com.example.cusa4.configurations;

import com.example.cusa4.models.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataBaseConfig {
    @Bean
    public DriverManagerDataSource createDB() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost/cursa4?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("Yunglean2001");
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate createJdbc() {
        return new JdbcTemplate(createDB());
    }

    @Bean
    public RowMapper<User> createUserMapper() {
        return new BeanPropertyRowMapper<>(User.class);
    }

    @Bean
    public RowMapper<Boxer> createBoxerMapper() {
        return new BeanPropertyRowMapper<>(Boxer.class);
    }

    @Bean
    public RowMapper<Fight> createFightMapper() {
        return new BeanPropertyRowMapper<>(Fight.class);
    }

    @Bean
    public RowMapper<Title> createTitleMapper() {
        return new BeanPropertyRowMapper<>(Title.class);
    }

    @Bean
    public RowMapper<Coach> createCoachMapper() {
        return new BeanPropertyRowMapper<>(Coach.class);
    }

    @Bean
    public RowMapper<OlympiadDto> createOlympiadMapper() {
        return new BeanPropertyRowMapper<>(OlympiadDto.class);
    }



    @Bean
    public RowMapper<FightInFightNight> createFightInFightNightMapper() {
        return new BeanPropertyRowMapper<>(FightInFightNight.class);
    }

    @Bean
    public RowMapper<Judge> createJudgeMapper() {
        return new BeanPropertyRowMapper<>(Judge.class);
    }

    @Bean
    public RowMapper<ProfBoxEvent> createProfBoxEventMapper() {
        return new BeanPropertyRowMapper<>(ProfBoxEvent.class);
    }
}