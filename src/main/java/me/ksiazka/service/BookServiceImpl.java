package me.ksiazka.service;

import me.ksiazka.dao.BookDAO;
import me.ksiazka.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDAO bookDAO;

    @Override
    @Transactional
    public long save(Book toSave) {
        return 0;
    }

    @Override
    @Transactional
    public Book get(long id) {
        return null;
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return null;
    }

    @Override
    @Transactional
    public void update(Book toUpdate) {

    }

    @Override
    @Transactional
    public void delete(Book toDelete) {

    }
}
