package com.toolkit.app.service;

import com.toolkit.app.dao.UserDAO;
import com.toolkit.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserDAO> getUsers()
    {
         return userRepository.findAllUsers();
    }

    public List<UserDAO> login(String username, String password, String role)
    {
        return userRepository.login(username,password,role);
    }

}
