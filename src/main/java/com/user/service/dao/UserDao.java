package com.user.service.dao;

import com.user.service.domain.User;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by yury on 7/28/16.
 */
public interface UserDao {
    public List<User> getAllUsers();
    public Integer addUser(User user) throws NoSuchAlgorithmException;
    public User getUserById(Integer id);
    public User getUserByLogin(String login);
    public Integer updateUser(User user) throws NoSuchAlgorithmException;
    public Integer deleteUser(Integer id);
    public void setToken(Integer id,BigInteger token);
    public User getUserByToken(String token);
}
