package me.ksiazka.service;

import me.ksiazka.dao.BookDAO;
import me.ksiazka.dao.UserDAO;
import me.ksiazka.misc.BookLists;
import me.ksiazka.model.Book;
import me.ksiazka.model.BookStatus;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDAO bookDAO;

    @Autowired
    UserDAO userDAO;

    //Ilosc ksiazek na strone.
    @Autowired
    private Integer bookLimitOnPage;

    @Override
    @Transactional
    public long save(Book toSave){
        toSave.setBookStatus(BookStatus.AWAITING);
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
        return bookDAO.getAll();
    }

    @Override
    @Transactional
    public void update(Book toUpdate) {
        bookDAO.update(toUpdate);
    }

    @Transactional
    public void hardDelete(Book toDelete){
        List<User> list = (List<User>) userDAO.getUsersForBookHardDelete(toDelete);

        Iterator lit = list.iterator();
        while(lit.hasNext()){
            User u = (User) lit.next();
            u.getBooksWant().remove(toDelete);

            Iterator uit = u.getBooksHave().iterator();
            while(uit.hasNext()){
                UserBook ub = (UserBook) uit.next();
                if(ub.getBook().equals(toDelete)) {
                    u.getBooksHave().remove(ub);
                    break;
                }
            }
        }
        bookDAO.delete(toDelete);
    }

    @Override
    @Transactional
    public void delete(Book toDelete) {
        bookDAO.delete(toDelete);
    }

/*    @Override
    @Transactional
    public List<Book> lastBooksAdded(int page) {
        return bookDAO.getLastBooks(page, bookLimitOnPage);
    }*/

    @Override
    public List<Book> getAllAccepted() {
        return bookDAO.getAllAccepted();
    }

    @Override
    public List<User> findEachUserWithBookInHaveList(long bookId) {
        return bookDAO.findEachUserWithBookInHaveList(bookId);
    }

    @Override
    public List<User> findEachUserWithBookInWantList(long bookId) {
        return bookDAO.findEachUserWithBookInWantList(bookId);
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

    @Transactional
    public int checkMaxPagesLimit() {
        double maxNumberPage = Math.floor((double) bookDAO.getAll().size() / bookLimitOnPage);
        if (bookDAO.getAll().size() % 5 == 0) return (int) maxNumberPage - 1;
        return (int) maxNumberPage;
    }

    @Override
    @Transactional
    public BookLists bookLists(int lastBooksAddedPage, int mostPopularBooksPage){
        BookLists bookLists = new BookLists();
        bookLists.setLastBooksAdded(bookDAO.getLastBooks(lastBooksAddedPage, bookLimitOnPage));
        bookLists.setMostPopularBooks(bookDAO.getMostPopularBooks(mostPopularBooksPage, bookLimitOnPage));
        bookLists.setMaxPages(this.checkMaxPagesLimit());
        return bookLists;
    }
}
