package com.user.service.client;


import com.user.service.UserService;
import com.user.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
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

    @RequestMapping("/registration")
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView("registration","user",new User());
        return modelAndView;
    }

    @RequestMapping("/addUser")
    public String addUser(User user) throws NoSuchAlgorithmException {
        userService.addUser(user);
        ModelAndView modelAndView = new ModelAndView("registration");
        return "redirect:/login";
    }
}
