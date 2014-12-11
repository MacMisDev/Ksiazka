package me.ksiazka.controller;


import me.ksiazka.model.User;
import me.ksiazka.service.AuthService;
import me.ksiazka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller("authController")
public class AuthControllerImpl implements AuthController{

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Principal principal) {
        if(principal != null){
            return "redirect:/home";
        }else{
            return "login";
        }
    }

    @Override
    @RequestMapping(value = "/login", params = "error", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error") String error, Model model) {
        model.addAttribute("error", "Zly email albo haslo!");
        return "login";
    }

    @Override
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @Override
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid User user,
                           BindingResult bindingResult){

        if (!bindingResult.hasErrors()) {
            authService.includeRoles(user);
            try {
                userService.save(user);
            } catch (DataIntegrityViolationException e) {
                user.setPassword("");

                if (userService.findUserByEmail(user.getEmail()) != null && userService.findUserByUsername(user.getUsername()) != null) {
                    bindingResult.rejectValue("email", "error.user", "Taki email istnieje już w bazie!");
                    bindingResult.rejectValue("username", "error.user", "Taka nazwa uzytkownika istnieje już w bazie!");
                    return "register";
                } else if (userService.findUserByUsername(user.getUsername()) != null) {
                    bindingResult.rejectValue("username", "error.user", "Taka nazwa uzytkownika istnieje już w bazie!");
                    return "register";
                } else {
                    bindingResult.rejectValue("email", "error.user", "Taki email istnieje już w bazie!");
                    return "register";
                }
            }
        } else {
            return "register";
        }


        return "redirect:/login";
    }

    @Override
    @RequestMapping(value = "/login", params = "logout", method = RequestMethod.GET)
    public String logout(@RequestParam(value = "logout") String info, Model model) {
        model.addAttribute("logout", "Wylogowano!");
        return "login";
    }

}
