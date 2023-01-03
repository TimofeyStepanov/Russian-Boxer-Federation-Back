package com.example.cusa4.services;

import com.example.cusa4.exceptions.WrongOlympiadIdException;
import com.example.cusa4.models.Boxer;
import com.example.cusa4.models.OlympiadDto;

import java.util.List;

public interface OlympiadService {
    List<Boxer> getCurrentOlympiadCrew(String pole);

    List<OlympiadDto> getAllOlympiad();

    List<Boxer> getOlympiadCrew(int olympiadId) throws WrongOlympiadIdException;
}
