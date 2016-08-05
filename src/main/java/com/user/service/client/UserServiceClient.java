package com.user.service.client;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.user.service.UserService;
import com.user.service.domain.Image;
import com.user.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.*;


@Controller
@RequestMapping(headers = "Accept=*/*")
public class UserServiceClient {

    private Cloudinary cloudinary;

    private final ResourceLoader resourceLoader;

    @Autowired
    public UserServiceClient(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "blondeks",
                "api_key", "722379398242111",
                "api_secret", "gfcVheW-iL7gx76-7acl4HFKDes"
        ));
    }

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
        return "redirect:/login";
    }
    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id) {
        Integer code = userService.deleteUser(id);
            return "redirect:/main";
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
        return "redirect:/login";
    }


    @RequestMapping(value = "/logout")
    public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    request.getSession().invalidate();
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        return "redirect:/login";
    }

    @RequestMapping("/addImgForm")
    public ModelAndView provideUploadInfo(HttpServletRequest request) throws IOException, ParseException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token") && !cookie.getValue().equals("")) {
                    return new ModelAndView("uploadForm", "user", userService.getUserByToken(cookie.getValue()));
                }
            }
        }
        User user = new User();
        return new ModelAndView("login","user",user);
    }


    @RequestMapping(value = "/addImg/{userId}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, @PathVariable Integer userId) {

        if (!file.isEmpty()) {
            try {
                File convFile = new File(file.getOriginalFilename());
                file.transferTo(convFile);
                Map result = cloudinary.uploader().upload(convFile, Collections.emptyMap());
                String url = cloudinary.url().format("jpg").generate(result.get("public_id").toString());
                userService.addImg(url,userId);
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + file.getOriginalFilename() + "!");
            } catch (IOException|RuntimeException e) {
                redirectAttributes.addFlashAttribute("message", "Failued to upload " + file.getOriginalFilename() + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }

        return "redirect:/main";
    }

    @RequestMapping(value = "/deleteImg", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void deleteImage(@RequestParam("imgId") Integer imgId,@RequestParam("pubId") String pubId) throws IOException {
        cloudinary.uploader().destroy(pubId,Collections.emptyMap());
        userService.deleteImg(imgId);
    }

    public ModelAndView processLogin(HttpServletRequest request,String page) throws ParseException {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token") && !cookie.getValue().equals("")) {
                    if(page.equals("login")) {
                        page = "main";
                    }
                    return getView(page, cookie);
                }
            }
        }
        User user = new User();
        return new ModelAndView("login","user",user);
    }

    public ModelAndView getView(String page, Cookie cookie) throws ParseException {
        ModelAndView modelAndView = new ModelAndView(page);
        User user = userService.getUserByToken(cookie.getValue());
        List<Image> images = userService.getUserImages(user.getUserId());
        modelAndView.addObject("user", user);
        if(images!=null) {
            modelAndView.addObject("imgsResized", getResizedImagesUrl(images,0));
            modelAndView.addObject("imgs", images);
        }
        return modelAndView;
    }

    public List<Image> getResizedImagesUrl(List<Image> images,int flag) {
        List<Image> resizedImages = cloneList(images);
        for(Image image : resizedImages) {
            String url = "";
            String[] elementsOfPath = image.getUrl().split("/");
            for(int i=0;i<elementsOfPath.length - 1; i++) {
                url += elementsOfPath[i] + "/";
            }
            url += "c_thumb,h_100,w_150/" + elementsOfPath[elementsOfPath.length-1];
            image.setUrl(url);
        }
        return resizedImages;
    }

    public List<Image> cloneList(List<Image> imageList) {
        List<Image> clonedList = new ArrayList<Image>(imageList.size());
        for (Image image : imageList) {
            clonedList.add(new Image(image));
        }
        return clonedList;
    }
}
