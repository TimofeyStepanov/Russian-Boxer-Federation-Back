package com.example.cusa4.models;

import lombok.Data;

@Data
public class User {
    private int id;
    private String userMail;
    private String userPassword;
    private String token;
    private String userRole;
}
