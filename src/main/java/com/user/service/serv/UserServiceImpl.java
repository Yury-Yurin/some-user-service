package com.user.service.serv;

import com.user.service.UserService;
import com.user.service.domain.User;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {


    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger();

    @Autowired
    UserService userDao;

    public List<User> getAllUsers() {
        LOGGER.info("get all users service");
        return userDao.getAllUsers();
    }

    public Integer addUser(User user) throws NoSuchAlgorithmException {
        if(userDao.getUserByLogin(user.getLogin())!=null)
            return -1;
        user.setPassword(getPassHash(user.getPassword()));
        LOGGER.info("add user with login: " + user.getLogin());
        return userDao.addUser(user);
    }

    public User getUserById(Integer id) {
        LOGGER.info("get user by id: " + id);
        return userDao.getUserById(id);
    }

    public User getUserByLogin(String login) {
        LOGGER.info("get user by login: " + login);
        return userDao.getUserByLogin(login);
    }

    public Integer updateUser(User user) throws NoSuchAlgorithmException {
        LOGGER.info("update user with id: " + user.getUserId());
        user.setPassword(getPassHash(user.getPassword()));
        return userDao.updateUser(user);
    }

    public String getPassHash(String pass) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        byte[] array = messageDigest.digest(pass.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
}
