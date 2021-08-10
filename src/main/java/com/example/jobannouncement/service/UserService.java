package com.example.jobannouncement.service;

import com.example.jobannouncement.repository.UserRepository;
import com.example.jobannouncement.rest.controller.models.UserRequestModel;
import com.example.jobannouncement.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void create(UserRequestModel userRequestModel){
        User user = new User();
        user.setUserName(userRequestModel.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(userRequestModel.getPassword()));
        userRepository.save(user);
    }


    public int uniqueUserName(String userName){
        User user = userRepository.findUserByUserName(userName);
        if(user != null){
            return 1;
        }
        return 0;

    }



}
