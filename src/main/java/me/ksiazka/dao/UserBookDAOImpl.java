package me.ksiazka.dao;

import me.ksiazka.model.Book;
import me.ksiazka.model.UserBook;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserBookDAOImpl implements UserBookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long save(UserBook toSave) {
        return (Long) this.sessionFactory.getCurrentSession().save(toSave);
    }

    @Override
    public UserBook get(long id) {

        UserBook ub = (UserBook) this.sessionFactory.getCurrentSession().get(UserBook.class, id);
        return ub;

    }

    @Override
    public List<UserBook> getAll() {
        String q = "FROM UserBook";
        Query query =  this.sessionFactory.getCurrentSession().createQuery(q);
        List<UserBook> list = (List<UserBook>) query.list();
        return list;
    }

    @Override
    public void update(UserBook toUpdate) {
        this.sessionFactory.getCurrentSession().update(toUpdate);
    }

    @Override
    public void delete(UserBook toDelete) {

    }

    @Override
    public void delete(long id) {
        UserBook ub = (UserBook) this.sessionFactory.getCurrentSession().get(UserBook.class, id);
        sessionFactory.getCurrentSession().delete(ub);
    }

    @Override
    public List<Book> getAllUserHaveBooks(long userId) {
        String q = "FROM Book WHERE bookId in (select book FROM UserBook WHERE userId=:userId)";
        Query query =  this.sessionFactory.getCurrentSession().createQuery(q);
        return (List<Book>) query.setParameter("userId", userId).list();
    }

    @Override
    public List<Book> getAllUserWantBooks(long userId) {
        //krzys napisz tutaj kod, ktory wybiera wszystkie ksiazki usera o danym userId z bookswant
        return null;
    }

    //Wykomentowane bo doublowalo funcjonalnosc, zostawiam, zeby jak Krzysiowi przyszlo
    //znowu do glowy to napisac, to bedzie widzial, ze raz juz napisal i bylo niepotrzebne
    /*@Override
    public List<UserBook> getUserBooks(long id) {

        String q = "FROM UserBook where userId=:userId";
        Query query = this.sessionFactory.getCurrentSession().createQuery(q);
        List list = (List<UserBook>) query.setParameter("userId", id).list();

        return list.isEmpty()?null:list;
    }*/
}
