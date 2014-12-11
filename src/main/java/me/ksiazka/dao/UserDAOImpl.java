package me.ksiazka.dao;

import me.ksiazka.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

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

    public List<User> getUsersForBookHardDelete(Book toDelete) {
        List<User> list;
        String query = "FROM User WHERE id in (select user FROM UserBook WHERE bookId=:id)";
        Query listQuery = this.sessionFactory.getCurrentSession().createQuery(query);
        list = listQuery.setParameter("id", toDelete.getId()).list();

        return list;
    }

    @Override
    public void update(User toUpdate) {

        this.sessionFactory.getCurrentSession().update(toUpdate);

    }

    @Override
    public void delete(User toDelete) {

        this.sessionFactory.getCurrentSession().delete(toDelete);

    }

    @Override
    public void delete(long id) {

        User u = (User) this.sessionFactory.getCurrentSession().get(User.class, id);
        this.sessionFactory.getCurrentSession().delete(u);

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
        return (User)list.get(0);

    }

    @Override
    public User findUserByEmailWithLists(String email) {

        String query = "FROM User where email=:email";
        Query userQuery = this.sessionFactory.getCurrentSession().createQuery(query);
        List list = userQuery.setParameter("email", email).list();
        if(list.isEmpty()){
            return null;
        }
        User user = (User)list.get(0);
        Hibernate.initialize(user.getBooksHave());
        Hibernate.initialize(user.getBooksWant());
        return user;

    }

    @Override
    public User getUserWithLists(long id) {
        User u = (User) this.sessionFactory.getCurrentSession().get(User.class, id);
        Hibernate.initialize(u.getBooksHave());
        Hibernate.initialize(u.getBooksWant());
        return u;
    }


    @Override
    public List<User> searchByEmail(String email) throws InterruptedException {

        return generateHibernateSearchQueryFor("email", email).list();

    }

    @Override
    public void updateOfferRelationBeforeDelete(User user) {

            Long id = user.getId();
            String query = "UPDATE OfferRelation set userId=1 where userId=:id";
            this.sessionFactory.getCurrentSession().createQuery(query).setParameter("id", id).executeUpdate();

    }

    @Override
    public void updateUserBookBeforeDelete(User user) {

        Long id = user.getId();
        String query = "UPDATE UserBook set userId=1 where userId=:id";
        this.sessionFactory.getCurrentSession().createQuery(query).setParameter("id", id).executeUpdate();

    }

    private org.hibernate.Query generateHibernateSearchQueryFor(String field, String searchParam) {

        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();

        org.apache.lucene.search.Query lQuery = queryBuilder.keyword().onFields(field).matching(searchParam).createQuery();
        org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(lQuery, User.class);

        return fullTextQuery;

    }

    @Override
    public List<User> getUsersForOfferDelete(Long offerId){
        List<User> list;
        String query = "FROM User WHERE id in (select user FROM OfferRelation WHERE offerId=:id)";
        Query listQuery = this.sessionFactory.getCurrentSession().createQuery(query);
        list = listQuery.setParameter("id", offerId).list();

        return list;
    }

}
