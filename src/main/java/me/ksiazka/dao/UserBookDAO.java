package me.ksiazka.dao;

import me.ksiazka.model.UserBook;

import java.util.List;

public interface UserBookDAO extends BasicDAOInterface<UserBook> {

    //Zwraca liste ksiazek uzytkownika
    List<UserBook> getUserBooks(long id);
}
