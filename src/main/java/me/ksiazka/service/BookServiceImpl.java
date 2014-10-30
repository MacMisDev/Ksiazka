package me.ksiazka.service;

import me.ksiazka.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Override
    public long save(Book toSave) {
        return 0;
    }

    @Override
    public Book get(long id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public void update(Book toUpdate) {

    }

    @Override
    public void delete(Book toDelete) {

    }
}
