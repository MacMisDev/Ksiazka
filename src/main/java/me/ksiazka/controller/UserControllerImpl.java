package me.ksiazka.controller;


import me.ksiazka.misc.BookWantHave;
import me.ksiazka.model.User;
import me.ksiazka.service.UserBookService;
import me.ksiazka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller("userController")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserBookService userBookService;

    @Override
    @RequestMapping(value = "/user/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody BookWantHave userHaveWant() {
        BookWantHave bookWantHave = new BookWantHave();
        bookWantHave.setUserHave(userBookService.getAllUserHaveBooks(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        bookWantHave.setUserWant(userBookService.getAllUserWantBooks(userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        bookWantHave.setHaveListPages(userBookService.checkPagesMaxLimit(bookWantHave.getUserHave().size()));
        bookWantHave.setWantListPages(userBookService.checkPagesMaxLimit(bookWantHave.getUserWant().size()));
        return bookWantHave;
    }

    @Override
    @RequestMapping(value = {"/", "home"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User showLoggedUserPage() {
        return userService.findUserByEmailWithLists(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User showUser(@PathVariable long id) {
        return userService.getUserWithLists(id);
    }

    @Override
    @RequestMapping(value = "/user/settings/edit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User editUserData() {
        return userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    @RequestMapping(value = "/user/settings/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User updateEditedUser(@RequestBody @Valid User user) {
        //todo rest validation
        try{
            userService.update(user);
        }catch(Exception e){
            System.out.println(e.toString());
        }

        return userService.findUserByEmail(user.getEmail());
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
