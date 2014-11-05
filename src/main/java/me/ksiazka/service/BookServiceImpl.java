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
    @Autowired
    private Integer bookLimitOnPage;

    @Override
    @Transactional
    public long save(Book toSave) {
        return bookDAO.save(toSave);
    }

    @Override
    @Transactional
    public Book get(long id) {

        return bookDAO.get(id);
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
        return bookDAO.getLastBooks(page, bookLimitOnPage);
    }

    @Override
    @Transactional
    public int checkMaxPagesLimit() {
        double maxNumberPage = Math.floor((double) bookDAO.getAll().size() / bookLimitOnPage);
        if(bookDAO.getAll().size()%10 == 0) return (int) maxNumberPage-1;
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
