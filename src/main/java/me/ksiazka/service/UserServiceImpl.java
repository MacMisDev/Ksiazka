package me.ksiazka.service;

import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.User;
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
    @Transactional
    public User get(long id) {

        return userDAO.get(id);
    }

    @Override
    @Transactional
    public List<User> getAll() {

        return userDAO.getAll();
    }

    @Override
    @Transactional
    public void update(User toUpdate) {

        userDAO.update(toUpdate);
    }

    @Override
    @Transactional
    public void delete(User toDelete) {

        userDAO.updateUserBookBeforeDelete(toDelete);
        userDAO.updateOfferRelationBeforeDelete(toDelete);
        userDAO.delete(toDelete);

    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }


    @Transactional
    public User findUserByEmailWithLists(String email){
        return userDAO.findUserByEmailWithLists(email);
    }
}
