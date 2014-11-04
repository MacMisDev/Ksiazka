package me.ksiazka.service;

import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.User;
import me.ksiazka.model.UserRole;
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
    @Transactional
    public long save(User toSave) {

        return userDAO.save(toSave);
    }

    @Override
    public User get(long id) {

        return userDAO.get(id);
    }

    @Override
    public List<User> getAll() {

        return userDAO.getAll();
    }

    @Override
    public void update(User toUpdate) {

        userDAO.update(toUpdate);
    }

    @Override
    public void delete(User toDelete) {
        /*
        @ToDo: Podmienienie referencji do uzytkownika w UserBook przed usunięciem - Krzysiu musi zrobic query czy co
         */
        userDAO.delete(toDelete);
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {

        User user = userDAO.findUserByEmail(email);
        return user;
    }

}
