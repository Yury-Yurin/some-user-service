package com.user.service.dao;

import com.user.service.UserService;
import com.user.service.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
