package com.example.cusa4.services;

import com.example.cusa4.exceptions.WrongDateFormatException;
import com.example.cusa4.models.FightInFightNight;
import com.example.cusa4.models.Judge;
import com.example.cusa4.models.ProfBoxEvent;
import com.example.cusa4.models.WrongFightNightIdException;

import java.util.List;

public interface ProfBoxService {
    List<ProfBoxEvent> getPastEvents();

    List<ProfBoxEvent> getFutureEvents();

    List<FightInFightNight> getFights(int fightNightId);

    List<Judge> getJudges(int fightNightId);

    List<ProfBoxEvent> getFights(String dateString);

    List<ProfBoxEvent> getAllFightNights();

    void deleteFightNight(int fightNightId);
}
