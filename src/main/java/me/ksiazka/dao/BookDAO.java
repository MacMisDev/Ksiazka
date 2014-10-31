package me.ksiazka.dao;
import me.ksiazka.model.Book;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;
import org.hibernate.SessionFactory;

import java.util.List;

public interface BookDAO extends BasicDAOInterface<Book> {

    //Zwraca wszystkich uzytkownikow, ktorzy posiadaja ta ksiazke na liscie have
    public List<User> findEachUserWithBookInHaveList(long bookId);

    //Zwraca wszystkich uzytkownikow, ktorzy posiadaja ta ksiazke na liscie want
    public List<User> findEachUserWithBookInWantList(long bookId);
}
