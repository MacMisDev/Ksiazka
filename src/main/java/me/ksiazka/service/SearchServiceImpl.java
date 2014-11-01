package me.ksiazka.service;

import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.Book;
import me.ksiazka.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public List<User> searchByEmail(String email) throws InterruptedException {

        return userDAO.searchByEmail(email);
    }

    @Override
    public List<User> searchByUsername(String username) {
        return null;
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> searchByAutohor(String author) {
        return null;
    }

    @Override
    @Autowired
    @Transactional
    public void reindex(SessionFactory sessionFactory) throws InterruptedException {

        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        fullTextSession.createIndexer().startAndWait();
    }
}
