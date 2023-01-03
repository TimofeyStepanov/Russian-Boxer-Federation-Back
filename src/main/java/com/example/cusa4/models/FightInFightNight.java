package com.example.cusa4.models;

import lombok.Data;

@Data
public class FightInFightNight {
    private int id;
    private int firstBoxerId;
    private int secondBoxerId;
    private String fightStatus;
    private int roundNumber;
}
