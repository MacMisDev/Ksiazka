package me.ksiazka.service;

import me.ksiazka.Wrapper.BookLists;
import me.ksiazka.model.Book;
import me.ksiazka.model.User;

import java.util.List;

public interface BookService extends BasicServiceInterface<Book> {

/*    public List<Book> lastBooksAdded(int page);*/
    public boolean checkPageNumberForPagination(int number);
    public int checkMaxPagesLimitForAccepted();
    public List<Book> getAllAccepted();
    public List<User> findEachUserWithBookInHaveList(long bookId);
    public List<User> findEachUserWithBookInWantList(long bookId);
    public void hardDelete(Book toDelete);
    public BookLists bookLists(int lastBooksAddedPage, int mostPopularBooksPage);
    public Book findBookByISBN(int isbn);
}
