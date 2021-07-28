package com.toolkit.app.service;

import com.toolkit.app.dao.UserDAO;
import com.toolkit.app.dao.UserSkillsDAO;
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

    public List<UserDAO> login(String email, String password, String role)
    {
        return userRepository.login(email,password,role);
    }

    public UserSkillsDAO getUserSkills(String userId){
        return userRepository.getUserSkills(Integer.valueOf(userId));
    }

    //LIMIT TO 3 SECONDARY SKILLS
    public void setUserSkills(UserSkillsDAO userSkillsDAO){
        userRepository.setUserSkills(userSkillsDAO);
    }
}
