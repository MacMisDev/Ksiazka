package me.ksiazka.service;

import me.ksiazka.model.Book;

import java.util.List;

public interface BookService extends BasicServiceInterface<Book> {
    public List<Book> lastBooksAdded(int page);
    public boolean checkPageNumberForPagination(int number);
    public int checkMaxPagesLimit();
}
