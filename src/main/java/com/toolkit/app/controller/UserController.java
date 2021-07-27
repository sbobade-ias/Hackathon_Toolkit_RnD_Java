package com.toolkit.app.controller;

import com.toolkit.app.dao.UserDAO;
import com.toolkit.app.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    UserService userService;

    @RequestMapping(value="/insert")
    public String index(){
        jdbc.execute("insert into test(name,email)values('javatpoint','java@javatpoint.com')");
        return"data inserted Successfully";
    }

    @RequestMapping(value = "/test")
    public String test(){
        System.out.println("Testkjdakdj......");
        return"Welcome ..";
    }

    @GetMapping(value ="/getUsers")
            public List<UserDAO> getUsers(){
        List<UserDAO> list = userService.getUsers();
        return list;
    }



}
