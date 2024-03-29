package me.ksiazka.controller;


import me.ksiazka.Wrapper.BookWantHave;
import me.ksiazka.model.User;
import me.ksiazka.service.UserBookService;
import me.ksiazka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller("userController")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserBookService userBookService;

    @Override
    @RequestMapping(value = "/user/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody BookWantHave userHaveWant() {
        BookWantHave bookWantHave = new BookWantHave();
        bookWantHave.setUserHave(userBookService.getAllUserHaveBooks(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        bookWantHave.setUserWant(userBookService.getAllUserWantBooks(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        bookWantHave.setHaveListPages(userBookService.checkPagesMaxLimit(bookWantHave.getUserHave().size()));
        bookWantHave.setWantListPages(userBookService.checkPagesMaxLimit(bookWantHave.getUserWant().size()));
        return bookWantHave;
    }

    @Override
    @RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
    public String showLoggedUserPage(Model model){
        model.addAttribute("user", userService.findUserByEmailWithLists(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "home";
    }

    @Override
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody User showUser(@PathVariable long id) {
        return userService.getUserWithLists(id);
    }

    @Override
    @RequestMapping(value = "/user/settings/edit", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody User editUserData() {
        return userService.findUserByEmailWithAddress(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    @RequestMapping(value = "/user/settings/edit", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public @ResponseBody User updateEditedUser(@Valid @RequestBody User user) {
        try{
            userService.update(user);
        }catch(Exception e){
            System.out.println(e.toString());
        }

        return userService.findUserByEmailWithAddress(user.getEmail());
    }

    @Override
    @RequestMapping(value = "/user/settings/delete", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public @ResponseBody String deleteAccount(HttpServletRequest request) {
        try {
            userService.delete(userService.findUserByEmailWithAddress(SecurityContextHolder.getContext().getAuthentication().getName()));
            request.logout();
        }catch (Exception e){
            //todo
        }
        return "true";
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
