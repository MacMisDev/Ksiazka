package me.ksiazka.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class HomeControllerImpl implements HomeController{

    @RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
    public String showHomePage(Model model) {
        return "home";
    }

}
