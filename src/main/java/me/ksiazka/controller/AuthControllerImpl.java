package me.ksiazka.controller;

import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.User;
import me.ksiazka.model.UserRole;
import me.ksiazka.service.AuthServiceImpl;
import me.ksiazka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("authController")
public class AuthControllerImpl implements AuthController{

    @Autowired
    private AuthServiceImpl authService;

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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerDone(User user, BindingResult bindingResult){

        if(!bindingResult.hasErrors()){
            authService.saveUser(user);
        }else{
            return "register";
        }

        return "redirect:/login";
    }

}
