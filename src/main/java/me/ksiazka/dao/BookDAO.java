package me.ksiazka.dao;
import me.ksiazka.model.Book;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;
import org.hibernate.SessionFactory;

import java.util.List;

public interface BookDAO extends BasicDAOInterface<Book> {

    //Pobiera ostatnie x dodanych ksiazek do bazy
    public List<Book> getLastBooks(int page, int bookLimitOnPage);

    //Pobiera x najpopularniejszych ksiazek
    public List<Book> getMostPopularBooks(int page, int bookLimitOnPage);

    //Zwraca wszystkich uzytkownikow, ktorzy posiadaja ta ksiazke na liscie have
    public List<User> findEachUserWithBookInHaveList(long bookId);

    //Zwraca wszystkich uzytkownikow, ktorzy posiadaja ta ksiazke na liscie want
    public List<User> findEachUserWithBookInWantList(long bookId);

    //Zwraca wszystkie ksiazki o statusie ACCEPTED
    public List<Book> getAllAccepted();

    //Query update'ujaca przed usunieciem Book
    public void updateUserBookBeforeDelete(Book book);
}
