package me.ksiazka.dao;

import me.ksiazka.model.Book;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
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
    public long testowySaveUserBook(Book book) {
        UserBook b = new UserBook();
        b.setTitle(book.getTitle());
        b.setAuthor(book.getAuthor());
        b.setBookCondition("Ok");
        User u = new User();
        u.setName("Jarke");
        sessionFactory.getCurrentSession().persist(u);

        User u2 = (User) this.sessionFactory.getCurrentSession().get(User.class, (long) 1);
        b.setUser(u2);

        sessionFactory.getCurrentSession().persist(b);

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
