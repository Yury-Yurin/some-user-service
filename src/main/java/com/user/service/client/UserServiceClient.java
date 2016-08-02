package com.user.service.client;


import com.user.service.UserService;
import com.user.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Controller
@RequestMapping(headers = "Accept=*/*")
public class UserServiceClient {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ModelAndView loginPage(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token") && cookie.getValue() != null) {
                    return new ModelAndView("main");
                }
            }
        }
        User user = new User();
        return new ModelAndView("login","user", user);
    }

    @RequestMapping("/registration")
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView("registration","user",new User());
        return modelAndView;
    }

    @RequestMapping("/addUser")
    public String addUser(User user) throws NoSuchAlgorithmException {
        userService.addUser(user);
        return "redirect:/main";
    }
    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id) {
        Integer code = userService.deleteUser(id);
            return "redirect:/main";
    }

    @RequestMapping("/main")
    public ModelAndView mainPage(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token") && cookie.getValue() != null) {
                    return new ModelAndView("main","user", userService.getUserByToken(cookie.getValue()));
                }
            }
        }
        User user = new User();
        return new ModelAndView("login","user",user);
    }

    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    public String signIn(User user, HttpServletResponse response) throws NoSuchAlgorithmException {
        response.addCookie(new Cookie("token", userService.signIn(user)));
        return "redirect:/main";
    }

    @RequestMapping(value = "/logout")
    public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.getSession().invalidate();
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setValue("");
                cookies[i].setPath("/");
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        return "redirect:login";
    }


}
