package me.ksiazka.dao;

import me.ksiazka.model.*;
import org.hibernate.CacheMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    static private boolean usersIndexed = false;

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


    //Plz dont touch now
    @Override
    public List<User> searchByEmail(String email) throws InterruptedException {
//
//        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
//        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();
//
//        org.apache.lucene.search.Query lQuery = queryBuilder.keyword().onFields("email").matching(email).createQuery();
//        org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(lQuery, User.class);
//
//        System.out.println(":: " + usersIndexed);
//
//        return fullTextQuery.list();
        return null;
    }

}
