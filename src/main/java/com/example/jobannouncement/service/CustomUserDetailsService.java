package com.example.jobannouncement.service;

import com.example.jobannouncement.repository.UserRepository;
import com.example.jobannouncement.service.model.CustomUserDetails;
import com.example.jobannouncement.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        User user = userRepository.findUserByUserName(s);
        customUserDetails.setUser(user);
        System.out.println(customUserDetails.getUsername());
        return customUserDetails;
    }
}
