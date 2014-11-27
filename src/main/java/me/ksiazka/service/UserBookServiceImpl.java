package me.ksiazka.service;

import me.ksiazka.dao.UserBookDAO;
import me.ksiazka.model.Book;
import me.ksiazka.model.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserBookServiceImpl implements BasicServiceInterface<UserBook>, UserBookService {

    @Autowired
    UserBookDAO userBookDAO;

    @Override
    @Transactional
    public long save(UserBook toSave) {
        return 0;
    }

    @Override
    @Transactional
    public UserBook get(long id) {
        return null;
    }

    @Override
    @Transactional
    public List<UserBook> getAll() {
        return null;
    }

    @Override
    @Transactional
    public void update(UserBook toUpdate) {
    }

    @Override
    @Transactional
    public void delete(UserBook toDelete) {
    }

    @Override
    @Transactional
    public List<Book> getAllUserHaveBooks(long userId) {
        return userBookDAO.getAllUserHaveBooks(userId);
    }

    @Override
    @Transactional
    public List<Book> getAllUserWantBooks(long userId) {
        return userBookDAO.getAllUserWantBooks(userId);
    }
}
