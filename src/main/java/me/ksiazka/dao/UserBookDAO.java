package me.ksiazka.dao;

import me.ksiazka.model.Book;
import me.ksiazka.model.UserBook;

import java.util.List;

public interface UserBookDAO extends BasicDAOInterface<UserBook> {

    //Double'uje istniejaca funkcjonalnosc
    //Zwraca liste ksiazek uzytkownika
    //public List<UserBook> getUserBooks(long id);
    public List<Book> getAllUserHaveBooks(long userId);
    public List<Book> getAllUserWantBooks(long userId);
}
