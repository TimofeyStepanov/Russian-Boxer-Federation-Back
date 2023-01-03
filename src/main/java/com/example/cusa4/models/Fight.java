package com.example.cusa4.models;

import lombok.Data;

import java.sql.Date;

@Data
public class Fight {
    private int id;
    private int fightNightId;
    private Date fightDate;
    private String fightStatus;
    private int roundNumber;
    private String result;
}
