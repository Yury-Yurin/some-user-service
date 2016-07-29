package com.user.service.client;


import com.user.service.UserService;
import com.user.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UserServiceClient {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ModelAndView loginPage() {
        List<User> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("login","users", users);
        return modelAndView;
    }
}
