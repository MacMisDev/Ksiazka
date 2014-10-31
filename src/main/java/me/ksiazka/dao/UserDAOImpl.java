package me.ksiazka.dao;

import me.ksiazka.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

//    @Override
//    public List<UserBook> getUserHaveList(long userId) {
//
//        List<UserBook> list;
//        String query = "FROM UserBook where userId=:id";
//        Query listQuery = this.sessionFactory.getCurrentSession().createQuery(query);
//        list = listQuery.setParameter("id", userId).list();
//
//        return list;
//    }
//
//    @Override
//    public UserBook getUserBook(long userBookId) {
//
//        UserBook ub = (UserBook) this.sessionFactory.getCurrentSession().get(UserBook.class, userBookId);
//        return ub;
//    }

    @Override
    @Transactional
    public long save(User toSave) {

        return (Long) this.sessionFactory.getCurrentSession().save(toSave);
    }

    @Override
    public User get(long id) {

        User u = (User) this.sessionFactory.getCurrentSession().get(User.class, id);
        return u;
    }

    @Override
    public List<User> getAll() {
        String query = "FROM User";
        List list = (List<User>) this.sessionFactory.getCurrentSession().createQuery(query).list();

        return list;
    }

    @Override
    public void update(User toUpdate) {
        this.sessionFactory.getCurrentSession().update(toUpdate);
    }

    @Override
    public void delete(User toDelete) {

    }

    @Override
    public void delete(long id) {
        User u = (User) this.sessionFactory.getCurrentSession().get(User.class, id);
        sessionFactory.getCurrentSession().delete(u);
    }

    @Override
    public User findUserByUsername(String username) {
        String query = "FROM User where username=:username";
        Query userQuery = this.sessionFactory.getCurrentSession().createQuery(query);
        List list = userQuery.setParameter("username", username).list();

        return list.isEmpty()?null:(User)list.get(0);
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        String query = "FROM User where email=:email";
        Query userQuery = this.sessionFactory.getCurrentSession().createQuery(query);
        List list = userQuery.setParameter("email", email).list();
        if(list.isEmpty()){
            return null;
        }
        User user = (User)list.get(0);
        return user;
    }


}
