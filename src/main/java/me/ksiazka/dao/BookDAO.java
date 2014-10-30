package me.ksiazka.dao;
import me.ksiazka.model.Book;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;
import org.hibernate.SessionFactory;

import java.util.List;

public interface BookDAO extends BasicDAOInterface<Book> {

    public List<UserBook> findEachInstanceOfBook(long id);
}
