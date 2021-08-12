package com.example.jobannouncement.rest.controller.models;

import com.example.jobannouncement.service.model.User;

public class LoginResponseModel {
    private String authentificationToken;
    private User user;

    public String getAuthentificationToken() {
        return authentificationToken;
    }

    public void setAuthentificationToken(String authentificationToken) {
        this.authentificationToken = authentificationToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
