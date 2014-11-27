package me.ksiazka.controller;

import me.ksiazka.misc.BookWantHave;
import me.ksiazka.model.User;
import org.springframework.ui.Model;


public interface UserController {
    public BookWantHave userHaveWant();
    public User showUserPage();
    public String editUserData(Long id, Model model);
    public String updateEditedUser(User user);
    /* -- Prawdopodobnie zrobimy to w przeływach (Web Flow)
    public String addBookToWantList(Model model);
    public String delBookFromWantList(Model model);
    public String addBookToHaveList(Model model);
    public String delBookFromHaveList(Model model);
    public String offerTrade();
    */
    public String deleteAccount(Model model);
    public String acceptTrade(Long tradeId);
    public String declineTrade(Long tradeId);
}
