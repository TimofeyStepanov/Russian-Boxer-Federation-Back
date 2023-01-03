package com.example.cusa4.services;

import com.example.cusa4.models.*;

import java.util.List;

public interface BoxerService {
    List<Boxer> getBoxers();
    Boxer getBoxer(int serviceNumber) throws WrongBoxerIdException;
    List<Fight> getFights(int serviceNumber);
    List<Coach> getCoaches(int serviceNumber);
    List<Title> getTitles(int serviceNumber);
    List<Boxer> getRespectedBoxers();
}
