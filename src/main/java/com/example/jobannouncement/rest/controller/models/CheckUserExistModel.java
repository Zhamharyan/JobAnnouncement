package com.example.jobannouncement.rest.controller.models;

import com.example.jobannouncement.service.model.User;

public class CheckUserExistModel {

    private String userDecodedPassword;
    private String authentificationToken;
    private User user;

    public String getAuthentificationToken() {
        return authentificationToken;
    }

    public void setAuthentificationToken(String authentificationToken) {
        this.authentificationToken = authentificationToken;
    }

    public String getUserDecodedPassword() {
        return userDecodedPassword;
    }

    public void setUserDecodedPassword(String userDecodedPassword) {
        this.userDecodedPassword = userDecodedPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}



//ecnrypt