package me.ksiazka.service;

import me.ksiazka.model.Book;
import me.ksiazka.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {

    //Wyszukiwanie w bazie po emailu (hibernate search)
    public List<User> searchByEmail(String email) throws InterruptedException;

    //Wyszukiwanie w bazie po username (hibernate search)
    public List<User> searchByUsername(String username);

    //Wyszukiwanie w bazie po tytule
    public List<Book> searchByTitle(String title);

    //Wyszukiwanie w bazie po autorze
    public List<Book> searchByAutohor(String author);

    //Wykonaj reindexowanie
    public void reindex(SessionFactory sessionFactory) throws InterruptedException;

}
