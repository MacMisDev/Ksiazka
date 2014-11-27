package me.ksiazka.misc;

import me.ksiazka.model.Book;

import java.util.List;

public class BookWantHave {
    public List<Book> userHave;
    public List<Book> userWant;
    int haveListPages;
    int wantListPages;

    public BookWantHave() {
    }

    public List<Book> getUserHave() {
        return userHave;
    }

    public void setUserHave(List<Book> userHave) {
        this.userHave = userHave;
    }

    public List<Book> getUserWant() {
        return userWant;
    }

    public void setUserWant(List<Book> userWant) {
        this.userWant = userWant;
    }

    public int getHaveListPages() {
        return haveListPages;
    }

    public void setHaveListPages(int haveListPages) {
        this.haveListPages = haveListPages;
    }

    public int getWantListPages() {
        return wantListPages;
    }

    public void setWantListPages(int wantListPages) {
        this.wantListPages = wantListPages;
    }
}
