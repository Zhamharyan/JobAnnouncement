package com.example.jobannouncement.service;

import com.example.jobannouncement.persistence.Gender;
import com.example.jobannouncement.repository.UserRepository;
import com.example.jobannouncement.rest.controller.models.CheckUserExistModel;
import com.example.jobannouncement.rest.controller.models.LoginRequestModel;
import com.example.jobannouncement.rest.controller.models.UserRequestModel;
import com.example.jobannouncement.rest.controller.models.register.RegisterRequestModel;
import com.example.jobannouncement.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void create(RegisterRequestModel registerRequestModel){
        User user = new User();
        user.setUserName(registerRequestModel.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequestModel.getPassword()));
        user.setEmail(registerRequestModel.getEmail());
        if (registerRequestModel.getGender().toLowerCase().equals("male")){
            user.setGender(Gender.MALE);
        }else if(registerRequestModel.getGender().toLowerCase().equals("female")){
            user.setGender(Gender.FEMALE);
        }else{
            user.setGender(Gender.OTHER);
        }
        user.setFullName(registerRequestModel.getFullName());
        user.setDateOfBirth(new Date(Long.parseLong(registerRequestModel.getDateOfBirth())));
        userRepository.save(user);
    }


    public int uniqueUserName(String userName){
        User user = userRepository.findUserByUserName(userName);
        if(user != null){
            return 1;
        }
        return 0;
    }

    public String signIn(LoginRequestModel loginRequestModel){
        User user = userRepository.findUserByUserName(loginRequestModel.getUserName());
        String authentificationToken = null;
        if(user != null){
            String passwordEncoded = user.getPassword();
            String passwordDecoded = loginRequestModel.getPassword();
            if(bCryptPasswordEncoder.matches(passwordDecoded,passwordEncoded)){
                   authentificationToken = new String(Base64.getEncoder().encode((
                           loginRequestModel.getUserName()+":"+passwordDecoded).getBytes()));
            }
        }
        return authentificationToken;
    }




}
