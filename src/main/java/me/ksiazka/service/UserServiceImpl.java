package me.ksiazka.service;

import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public long save(User toSave) {
        return userDAO.save(toSave);
    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void update(User toUpdate) {

    }

    @Override
    public void delete(User toDelete) {

    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {

        User user = userDAO.findUserByEmail(email);
        return user;
    }
}
