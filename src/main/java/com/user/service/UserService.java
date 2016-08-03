package com.user.service;

import com.user.service.domain.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by yury on 7/28/16.
 */
public interface UserService {
    public List<User> getAllUsers();
    public Integer addUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
    public User getUserById(Integer id);
    public User getUserByLogin(String login);
    public Integer updateUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
    public Integer deleteUser(Integer id);
    public String signIn(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
    public User getUserByToken(String token) throws ParseException;
}
