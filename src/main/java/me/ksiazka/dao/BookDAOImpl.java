package me.ksiazka.dao;

import me.ksiazka.model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Component
@Transactional
public class BookDAOImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Book getBook(long bookId) {

        Book b = (Book) this.sessionFactory.getCurrentSession().get(Book.class, bookId);
        return b;
    }

    @Override
    public Long saveBook(Book book) {
        Long id = (Long) this.sessionFactory.getCurrentSession().save(book);

        return id;
    }

    @Override
    public void deleteBook(long bookId) {

        Book b = (Book) this.sessionFactory.getCurrentSession().get(Book.class, bookId);
        sessionFactory.getCurrentSession().delete(b);

    }

    @Override
    public void updateBook(Book updatedBook) {
        this.sessionFactory.getCurrentSession().update(updatedBook);
    }


    @Override
    public List<Book> getAll() {
        List<Book> list;
        String query = "FROM Book";
        Query listQuery = this.sessionFactory.getCurrentSession().createQuery(query);
        list = listQuery.list();

        return list;
    }

    @Override
    public List<UserBook> getAllInstancesOfThisBook(long bookId) {
        return null;
    }
}
