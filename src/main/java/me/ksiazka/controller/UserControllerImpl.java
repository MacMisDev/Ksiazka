package me.ksiazka.controller;


import me.ksiazka.model.User;
import me.ksiazka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("userController")
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;


    public String showUserPage(Long id, Model model) {
        return null;
    }


    public String editUserData(Long id, Model model) {
        return null;
    }


    public String updateEditedUser(User user) {
        return null;
    }


    public String deleteAccount(Model model) {
        return null;
    }


    public String acceptTrade(Long tradeId) {
        return null;
    }


    public String declineTrade(Long tradeId) {
        return null;
    }
}
