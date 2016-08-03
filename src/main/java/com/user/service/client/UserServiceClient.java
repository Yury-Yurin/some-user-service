package com.user.service.client;


import com.user.service.UserService;
import com.user.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Enumeration;


@Controller
@RequestMapping(headers = "Accept=*/*")
public class UserServiceClient {


    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ModelAndView loginPage(HttpServletRequest request) throws ParseException {
        return processLogin(request,"login");
    }

    @RequestMapping("/registration")
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView("registration","user",new User());
        return modelAndView;
    }

    @RequestMapping("/addUser")
    public String addUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        userService.addUser(user);
        return "redirect:login";
    }
    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id) {
        Integer code = userService.deleteUser(id);
            return "redirect:main";
    }

    @RequestMapping("/main")
    public ModelAndView mainPage(HttpServletRequest request) throws ParseException {
       return processLogin(request,"main");
    }

    @RequestMapping("/about")
    public ModelAndView aboutPage(HttpServletRequest request) throws ParseException {
        return processLogin(request,"about");
    }

    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    public String signIn(User user, HttpServletResponse response) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String token = userService.signIn(user);
        if(token!=null) {
            Cookie cookie = new Cookie("token", userService.signIn(user));
            cookie.setMaxAge(3600000);
            response.addCookie(cookie);
            return "redirect:main";
        }
        return "redirect:login";
    }

    @RequestMapping(value = "/logout")
    public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        return "redirect:login";
    }

    public ModelAndView processLogin(HttpServletRequest request,String page) throws ParseException {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token") && !cookie.getValue().equals("")) {
                    if(page.equals("main") || page.equals("login"))
                        return new ModelAndView("main","user", userService.getUserByToken(cookie.getValue()));
                    if(page.equals("about"))
                        return new ModelAndView("aboutUser","user", userService.getUserByToken(cookie.getValue()));
                }
            }
        }
        User user = new User();
        return new ModelAndView("login","user",user);
    }
}
