package me.ksiazka.dao;

import me.ksiazka.model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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


    @Override
    public List<Book> getLastBooks(int page, int BookLimitOnPage) {

        Query query = sessionFactory.getCurrentSession().createQuery("from Book where bookStatus='Accepted' order by bookId desc");
        query.setMaxResults(BookLimitOnPage);
        query.setFirstResult(page * BookLimitOnPage);

        return (List<Book>) query.list();
    }

    //Zapewne da sie to zrobic jakims zlaczeniem hql-owym. Daloby sie pewnie tez
    //zrobic to przeszukujac obiekty, ale zapytanie do bazy bedzie milion razy
    //efektywniejsze, dlatego ta metoda droga wyjatku chociaz wykonuje pewna logike
    //znajduje sie w dao
    @Override
    public List<User> findEachUserWithBookInHaveList(long bookId) {
        List<User> list;
        String query = "FROM User WHERE id in (select user FROM UserBook WHERE bookId=:id)";
        Query listQuery = this.sessionFactory.getCurrentSession().createQuery(query);
        list = listQuery.setParameter("id", bookId).list();
        return list;
    }

    //j.w.
    @Override
    public List<User> findEachUserWithBookInWantList(long bookId) {
        List<User> list;
        String query = "SELECT u FROM User u join u.booksWant b where b.id=:id)";
        Query listQuery = this.sessionFactory.getCurrentSession().createQuery(query);
        list = listQuery.setParameter("id", bookId).list();
        return list;
    }

    @Override
    public List<Book> getAllAccepted() {
        return null;
    }

    @Override
    public void updateUserBookBeforeDelete(Book book) {

    }
}
