package me.ksiazka.dao;

import me.ksiazka.model.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class BookDAOImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Book getBook(long bookId) {

        Book b = (Book) this.sessionFactory.getCurrentSession().get(Book.class, bookId);
        return b;
    }

    @Override
    @Transactional
    public long saveBook(Book book) {
        return 0;
    }

    @Override
    @Transactional
    public void deleteBook(long bookId) {

    }

    @Override
    @Transactional
    public void updateBook(long bookId, Book updatedBook) {

    }
}
