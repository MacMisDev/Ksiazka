package me.ksiazka.dao;

import me.ksiazka.model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

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
    public Long saveBook(Book book) {
        return new Long(0);
    }

    @Override
    @Transactional
    public void deleteBook(long bookId) {

        Book b = (Book) this.sessionFactory.getCurrentSession().get(Book.class, bookId);
        sessionFactory.getCurrentSession().delete(b);

    }

    @Override
    @Transactional
    public void updateBook(long bookId, Book updatedBook) {

    }


    @Override
    @Transactional
    public Long testowySaveUserBook(User user, Book book) {

        UserBook ub = new UserBook();
        ub.setUser(user);
        ub.setBook(book);
        ub.setBookCondition(Condition.GOOD);

        Long id = (Long) this.sessionFactory.getCurrentSession().save(ub);

        return id;
    }

    @Override
    @Transactional
    public UserBook getUserBook(long bookid){

        UserBook ub = (UserBook) this.sessionFactory.getCurrentSession().get(UserBook.class, bookid);
        return ub;

    }
}
