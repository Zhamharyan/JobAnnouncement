package com.example.jobannouncement.repository;

import com.example.jobannouncement.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByUserName(String UserName);

}
