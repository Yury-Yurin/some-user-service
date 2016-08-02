package com.user.service.dao;

import com.user.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.security.NoSuchAlgorithmException;

@ContextConfiguration("classpath:spring-context.xml")
public class UserServiceDaoTest {

    @Autowired
    UserService userDao;

    @Autowired
    UserService userService;

    @Autowired
    UserService restUserService;

    @Test
    public void getAllUsers() throws NoSuchAlgorithmException {

    }
}
