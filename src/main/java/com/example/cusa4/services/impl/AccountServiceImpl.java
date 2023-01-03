package com.example.cusa4.services.impl;



import com.example.cusa4.exceptions.AccountIsExistingException;
import com.example.cusa4.exceptions.AccountNotFoundException;
import com.example.cusa4.models.User;
import com.example.cusa4.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRowMapper;

    @Override
    public User login(String email, String password) throws AccountIsExistingException {
        List<User> userList = jdbcTemplate.query(
                "select * from userInfo where userMail = ?",
                userRowMapper,
                email
        );

        if (userList.size() != 0) {
            throw new AccountIsExistingException();
        }

        jdbcTemplate.update(
                "insert into userInfo(userMail, userPassword) values (?,?)",
                email,
                password
        );

        userList = jdbcTemplate.query(
                "select * from userInfo where userMail = ?",
                userRowMapper,
                email
        );
        return userList.get(0);
    }

    @Override
    public User signUp(String email, String password) throws AccountNotFoundException {
        List<User> userList = jdbcTemplate.query(
                "select * from userInfo where userMail = ? and userPassword = ?",
                userRowMapper,
                email, password);

        if (userList.size() == 0) {
            throw new AccountNotFoundException();
        }

        User user = userList.get(0);
        String token = generateToken(user.getId());

        jdbcTemplate.update(
                "update userInfo set token = ? where id = ?",
                token,
                user.getId()
        );

        user.setToken(token);
        return user;
    }

    @Override
    public User get(int id) throws AccountNotFoundException {
        List<User> userList = jdbcTemplate.query(
                "select * from userInfo where id = ?",
                userRowMapper,
                id
        );

        if (userList.size() == 0) {
            throw new AccountNotFoundException();
        }
        return userList.get(0);
    }

    private String generateToken(int id) {
        // TODO:
        return "token" + id;
    }
}
