package me.ksiazka.dao;

import me.ksiazka.model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUser(long userId) {

        User u = (User) this.sessionFactory.getCurrentSession().get(User.class, userId);
        return u;

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public long saveUser(User user) {
        return 0;
    }

    @Override
    public void deleteUser(long userId) {

    }

    @Override
    public void updateUser(long userId, User updatedUser) {

    }

    @Override
    public List<Offer> getUserOffers(long userId, String offerStatus) {
        return null;
    }

    @Override
    public List<Address> getUserAddresses(long userId) {
        return null;
    }

    @Override
    public long addToHaveList(User user, Book book, Condition bookCondition) {

        UserBook ub = new UserBook();
        ub.setUser(user);
        ub.setBook(book);
        ub.setBookCondition(bookCondition);

        Long id = (Long) this.sessionFactory.getCurrentSession().save(ub);

        return id;
    }

    @Override
    public void deleteFromHaveList(long userId, long userBookId) {

    }

    @Override
    public void updateBookFromHaveList(long userBookId, UserBook updatedUserBook) {

    }

    @Override
    public List<UserBook> getUserHaveList(long userId) {

        List<UserBook> list;
        String query = "FROM UserBook where userId=:id";
        Query listQuery = this.sessionFactory.getCurrentSession().createQuery(query);
        list = listQuery.setParameter("id", userId).list();

        return list;
    }

    @Override
    public UserBook getUserBook(long userBookId) {

        UserBook ub = (UserBook) this.sessionFactory.getCurrentSession().get(UserBook.class, userBookId);
        return ub;
    }

    @Override
    public long addToWantList(long userId, long bookId) {
        return 0;
    }

    @Override
    public void deleteFromWantList(long userId, long bookId) {

    }
}
