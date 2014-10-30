package me.ksiazka.controller;

import me.ksiazka.model.User;
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
    private UserService userService;

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
            user.setPassword(hashPassword(user.getPassword()));
            //ustawienie role
            userService.save(user);
        }else{
            return "register";
        }

        return "login";
    }

    private boolean comparePasswords(String hashedPassword, String plainPassword){
        if(BCrypt.checkpw(plainPassword, hashedPassword)){
            return true;
        }else{
            return false;
        }
    }

    private String hashPassword(String pass){
        String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
        return hashedPass;
    }
}
