package com.user.service.serv;

import com.user.service.UserService;
import com.user.service.dao.UserDao;
import com.user.service.domain.User;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public Integer deleteUser(Integer id) {
        LOGGER.info("delete user by id: " + id);
        return userDao.deleteUser(id);
    }

    public String signIn(User user) throws NoSuchAlgorithmException {
        User userFromDB = userDao.getUserByLogin(user.getLogin());
        if(userFromDB != null) {
            if(userFromDB.getPassword().equals(getPassHash(user.getPassword()))) {
                BigInteger token = nextRandomBigInteger(startRand);
                userDao.setToken(userFromDB.getUserId(),nextRandomBigInteger(token));
                return token.toString();
            }
        }
        return null;
    }

    public User getUserByToken(String token) {
        return userDao.getUserByToken(token);
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

    public BigInteger nextRandomBigInteger(BigInteger n) {
        Random rand = new Random();
        BigInteger result = new BigInteger(n.bitLength(), rand);
        while( result.compareTo(n) >= 0 ) {
            result = new BigInteger(n.bitLength(), rand);
        }
        return result;
    }
}
