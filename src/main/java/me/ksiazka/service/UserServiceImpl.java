package me.ksiazka.service;

import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDao;

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

}
