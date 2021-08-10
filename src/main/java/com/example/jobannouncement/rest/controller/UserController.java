package com.example.jobannouncement.rest.controller;

import com.example.jobannouncement.repository.UserRepository;
import com.example.jobannouncement.rest.controller.models.UserRequestModel;
import com.example.jobannouncement.service.UserService;
import com.example.jobannouncement.service.model.CustomUserDetails;
import com.example.jobannouncement.service.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody String userJson){
        Gson gson = new Gson();
        UserRequestModel userRequestModel = gson.fromJson(userJson,UserRequestModel.class);
        userService.create(userRequestModel);
        String token = userRequestModel.getUserName()+":"+userRequestModel.getPassword();
        byte[] encode = Base64.getEncoder().encode(token.getBytes());
        String stringToken = new String(encode);
        System.out.println(stringToken);
        return stringToken;
    }

    @PostMapping("/signIn")
    public String signIn(){
        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Gson gson = new Gson();
        String userDetails = gson.toJson(details);
        return userDetails;

    }

    @GetMapping("/uniqueUserName")
    public int uniqueUserName(@RequestParam(value="userName") String userName){
        return userService.uniqueUserName(userName);
    }


}








