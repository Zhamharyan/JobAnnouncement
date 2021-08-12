package com.example.jobannouncement.rest.controller;

import com.example.jobannouncement.repository.UserRepository;
import com.example.jobannouncement.rest.controller.models.CheckUserExistModel;
import com.example.jobannouncement.rest.controller.models.LoginRequestModel;
import com.example.jobannouncement.rest.controller.models.LoginResponseModel;
import com.example.jobannouncement.rest.controller.models.UserRequestModel;
import com.example.jobannouncement.rest.controller.models.register.RegisterRequestModel;
import com.example.jobannouncement.service.UserService;
import com.example.jobannouncement.service.model.CustomUserDetails;
import com.example.jobannouncement.service.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;

@RestController
@CrossOrigin
public class UserController {
    Gson gson = new Gson();

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody String userRegisterJson){
        Gson gson = new Gson();
        RegisterRequestModel registerRequestModel = gson.fromJson(userRegisterJson,RegisterRequestModel.class);
        userService.create(registerRequestModel);
        String authToken = new String(Base64.getEncoder()
                .encode((registerRequestModel.getUserName()+":"+registerRequestModel.getPassword()).getBytes()));
        return ResponseEntity.ok(authToken);
    }
    @PostMapping("/authenticateAndGetUser")
    public String authenticateAndGetUser(){
        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails customUserDetails = (CustomUserDetails)details;
        String userDetails = gson.toJson(customUserDetails.getUser());
        return userDetails;
    }

    @GetMapping("/uniqueUserName")
    public int uniqueUserName(@RequestParam(value="userName") String userName){
        return userService.uniqueUserName(userName);
    }

    @PostMapping("/singIn")
    public String signIn(@RequestBody String logRequestModel){
        LoginRequestModel loginRequestModel = gson.fromJson(logRequestModel,LoginRequestModel.class);
        String authToken = userService.signIn(loginRequestModel);
        return authToken;
        /*if(checkUserExistModel.getUser() == null){
            return ResponseEntity.badRequest().body(null);
        }
        else{
            if(loginRequestModel.getPassword().equals(checkUserExistModel.getUserDecodedPassword())){
                LoginResponseModel loginResponseModel = new LoginResponseModel();
                loginResponseModel.setUser(checkUserExistModel.getUser());
                String stringToEncode = loginRequestModel.getUserName()+":"+loginRequestModel.getPassword();
                String encodedToken = new String(Base64.getEncoder().encode(stringToEncode.getBytes()));
                loginResponseModel.setAuthentificationToken(encodedToken);
                return ResponseEntity.ok(loginResponseModel);
            }
        }*/
    }


}








