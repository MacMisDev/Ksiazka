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

    //Ilosc ksiazek na strone.
    private int BookLimitOnPage = 5;

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

    @Override
    @Transactional
    public List<Book> lastBooksAdded(int page) {
        return bookDAO.getLastBooks(page, BookLimitOnPage);
    }

    @Override
    @Transactional
    public int checkMaxPagesLimit() {
        double maxNumberPage = Math.floor((double) bookDAO.getAll().size() / BookLimitOnPage);
        return (int) maxNumberPage;
    }

    @Override
    @Transactional
    public boolean checkPageNumberForPagination(int number) {
        int pageLimit = this.checkMaxPagesLimit();
        if(number > pageLimit){
            return false;
        }else{
            return true;
        }
    }
}
