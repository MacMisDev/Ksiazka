package me.ksiazka.dao;

import me.ksiazka.model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAOImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long save(Book toSave) {

        return (Long) this.sessionFactory.getCurrentSession().save(toSave);
    }

    @Override
    public Book get(long id) {

        return (Book) this.sessionFactory.getCurrentSession().get(Book.class, id);
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
    public void update(Book toUpdate) {

        this.sessionFactory.getCurrentSession().update(toUpdate);
    }

    @Override
    public void delete(Book toDelete) {

        Book b = (Book) this.sessionFactory.getCurrentSession().get(Book.class, toDelete.getId());
        sessionFactory.getCurrentSession().delete(b);
    }

    @Override
    public void delete(long id) {

        Book b = (Book) this.sessionFactory.getCurrentSession().get(Book.class, id);
        sessionFactory.getCurrentSession().delete(b);
    }

    //Ma znajdowac wszystkie instancje ksiazki Book o id = x posrod ksiazek UserBook.
    //Wystarczy jakies query typu select * from UserBook where bookId = x pewnie.
    @Override
    public List<UserBook> findEachInstanceOfBook(long id) {
        return null;
    }

}
