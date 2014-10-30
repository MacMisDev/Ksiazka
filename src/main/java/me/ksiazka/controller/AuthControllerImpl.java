package me.ksiazka.controller;

import me.ksiazka.model.User;
import me.ksiazka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("authController")
public class AuthControllerImpl implements AuthController{

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", params = "error", method = RequestMethod.GET)
    public String loginError(@RequestParam(value = "error", defaultValue = "Wrong email or password!") String error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String registerDone(User user){
        //todo
        return "home";
    }
}
