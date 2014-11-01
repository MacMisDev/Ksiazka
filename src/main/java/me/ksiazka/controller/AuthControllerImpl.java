package me.ksiazka.controller;

import me.ksiazka.model.User;
import me.ksiazka.service.AuthService;
import me.ksiazka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("authController")
public class AuthControllerImpl implements AuthController{

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", params = "error", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", defaultValue = "Wrong email or password!") String error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user, BindingResult bindingResult){

        if(!bindingResult.hasErrors()){
            authService.includeRoles(user);
            userService.save(user);
        }else{
            return "register";
        }

        return "redirect:/login";
    }

}
