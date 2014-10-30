package me.ksiazka.dao;

import me.ksiazka.model.UserBook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserBookDAOImpl implements UserBookDAO {

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
}
