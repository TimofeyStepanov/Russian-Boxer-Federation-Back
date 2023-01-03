package com.example.cusa4.services;

import com.example.cusa4.exceptions.AccountIsExistingException;
import com.example.cusa4.exceptions.AccountNotFoundException;
import com.example.cusa4.models.User;

public interface AccountService {
    User login(String email, String password) throws AccountIsExistingException;

    User signUp(String email, String password) throws AccountNotFoundException;

    User get(int id) throws AccountNotFoundException;
}
