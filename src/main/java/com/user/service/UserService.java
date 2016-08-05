package com.user.service;

import com.user.service.domain.Image;
import com.user.service.domain.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by yury on 7/28/16.
 */
public interface UserService {
    List<User> getAllUsers();
    Integer addUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
    User getUserById(Integer id);
    User getUserByLogin(String login);
    Integer updateUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
    Integer deleteUser(Integer id);
    String signIn(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
    User getUserByToken(String token) throws ParseException;
    List<Image> getUserImages(Integer userId);
    Integer addImg(String url, Integer userId);
    Integer deleteImg(Integer imgId);
}
