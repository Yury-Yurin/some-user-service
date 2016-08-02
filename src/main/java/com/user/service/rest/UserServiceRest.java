package com.user.service.rest;

import com.user.service.UserService;
import com.user.service.domain.User;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping(value = "/users", headers = "Accept=*/*")
public class UserServiceRest implements UserService {

    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger();

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        LOGGER.info("get all users rest");
        return userService.getAllUsers();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Integer addUser(@RequestBody User user) throws NoSuchAlgorithmException {
        LOGGER.info("add user rest");
        return userService.addUser(user);
    }

    @RequestMapping(value = "/user/byId",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@RequestParam("id") Integer id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/user/byLogin",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public User getUserByLogin(@RequestParam("login") String login) {
        return userService.getUserByLogin(login);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT,produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Integer updateUser(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/delete" , method = RequestMethod.DELETE, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer deleteUser(@RequestParam("id") Integer id) {
        return userService.deleteUser(id);
    }

    public String signIn(User user) throws NoSuchAlgorithmException {
        return null;
    }

    public User getUserByToken(String token) {
        return null;
    }
}
