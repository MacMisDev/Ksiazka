package me.ksiazka.controller;


import me.ksiazka.model.User;
import me.ksiazka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("userController")
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

    @Override
    public String showUserPage(Long id, Model model) {
        return null;
    }

    @Override
    public String editUserData(Long id, Model model) {
        return null;
    }

    @Override
    public String updateEditedUser(User user) {
        return null;
    }

    @Override
    public String deleteAccount(Model model) {
        return null;
    }

    @Override
    public String acceptTrade(Long tradeId) {
        return null;
    }

    @Override
    public String declineTrade(Long tradeId) {
        return null;
    }
}
