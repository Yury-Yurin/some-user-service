package com.user.service.serv;

import com.user.service.UserService;
import com.user.service.dao.UserDao;
import com.user.service.domain.Image;
import com.user.service.domain.User;
import org.apache.commons.lang.time.DateUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service("userService")
public class UserServiceImpl implements UserService {


    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger();
    private static final BigInteger startRand = new BigInteger("1234557678978756512345576789787565");

    @Autowired
    UserDao userDao;

    public List<User> getAllUsers() {
        LOGGER.info("get all users service");
        return userDao.getAllUsers();
    }

    public Integer addUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(userDao.getUserByLogin(user.getLogin())!=null)
            return -1;
        user.setPassword(SimpleMD5.MD5(user.getPassword()));
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

    public Integer updateUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        LOGGER.info("update user with id: " + user.getUserId());
        user.setPassword(SimpleMD5.MD5(user.getPassword()));
        return userDao.updateUser(user);
    }

    public Integer deleteUser(Integer id) {
        LOGGER.info("delete user by id: " + id);
        return userDao.deleteUser(id);
    }

    public String signIn(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        User userFromDB = userDao.getUserByLogin(user.getLogin());
        if(userFromDB != null) {
            if(userFromDB.getPassword().equals(SimpleMD5.MD5(user.getPassword()))) {
                BigInteger token = nextRandomBigInteger(startRand);
                userDao.setToken(userFromDB.getUserId(),token);
                return token.toString();
            }
        }
        return null;
    }

    public User getUserByToken(String token) throws ParseException {
        User user = userDao.getUserByToken(token);
        return user;
    }

    @Override
    public List<Image> getUserImages(Integer userId) {
        return userDao.getUserImages(userId);
    }

    @Override
    public Integer addImg(String url, Integer userId) {
        return userDao.addImg(url,userId);
    }

    @Override
    public Integer deleteImg(Integer imgId) {
        return userDao.deleteImg(imgId);
    }

    public BigInteger nextRandomBigInteger(BigInteger n) {
        Random rand = new Random();
        BigInteger result = new BigInteger(n.bitLength(), rand);
        while( result.compareTo(n) >= 0 ) {
            result = new BigInteger(n.bitLength(), rand);
        }
        return result;
    }
}
