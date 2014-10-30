package me.ksiazka.service;

import me.ksiazka.model.UserBook;

import java.util.List;

public class UserBookServiceImpl implements BasicServiceInterface<UserBook> {

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
}
