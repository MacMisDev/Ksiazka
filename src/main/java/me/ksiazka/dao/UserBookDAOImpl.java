package me.ksiazka.dao;

import me.ksiazka.model.UserBook;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserBookDAOImpl implements UserBookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long save(UserBook toSave) {
        return 0;
    }

    @Override
    public UserBook get(long id) {
        return null;
    }

    @Override
    public List<UserBook> getAll() {
        return null;
    }

    @Override
    public void update(UserBook toUpdate) {

    }

    @Override
    public void delete(UserBook toDelete) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<UserBook> getUserBooks(long id) {
        String q = "FROM UserBook where userId=:userId";
        Query query = this.sessionFactory.getCurrentSession().createQuery(q);
        List list = (List<UserBook>) query.setParameter("userId", id).list();

        return list.isEmpty()?null:list;
    }
}
