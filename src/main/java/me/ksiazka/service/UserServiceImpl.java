package me.ksiazka.service;

import me.ksiazka.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public long save(User toSave) {
        return 0;
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
}
