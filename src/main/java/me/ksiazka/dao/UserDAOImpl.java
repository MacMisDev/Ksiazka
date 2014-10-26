package me.ksiazka.dao;

import me.ksiazka.model.Address;
import me.ksiazka.model.Offer;
import me.ksiazka.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public User getUser(long userId) {

        User u = (User) this.sessionFactory.getCurrentSession().get(User.class, userId);
        return u;

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
}
