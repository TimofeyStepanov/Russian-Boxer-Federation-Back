package com.example.cusa4.controllers;


import com.example.cusa4.exceptions.AccountIsExistingException;
import com.example.cusa4.exceptions.AccountNotFoundException;
import com.example.cusa4.models.User;
import com.example.cusa4.security.models.OurAuthToken;
import com.example.cusa4.services.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/public/login")
    public User login(@RequestBody User user) throws AccountIsExistingException {
        return accountService.login(user.getUserMail(), user.getUserPassword());
    }

    @PostMapping("/public/signUp")
    public User signUp(@RequestBody User user) throws AccountNotFoundException {
        return accountService.signUp(user.getUserMail(), user.getUserPassword());
    }

    @GetMapping("/private/account")
    public User getAccount(OurAuthToken authToken) throws AccountNotFoundException {
        return accountService.get(authToken.getUserId());
    }
}
