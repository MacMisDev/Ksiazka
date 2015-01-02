package me.ksiazka.controller;

import me.ksiazka.Wrapper.BookWantHave;
import me.ksiazka.model.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;


public interface UserController {
    public BookWantHave userHaveWant();
    public String showLoggedUserPage(Model model);
    public User showUser(long id);
    public User editUserData();
    public User updateEditedUser(User user);
    /* -- Prawdopodobnie zrobimy to w przeływach (Web Flow)
    public String addBookToWantList(Model model);
    public String delBookFromWantList(Model model);
    public String addBookToHaveList(Model model);
    public String delBookFromHaveList(Model model);
    public String offerTrade();
    */
    public String deleteAccount(HttpServletRequest request);
    public String acceptTrade(Long tradeId);
    public String declineTrade(Long tradeId);
}
