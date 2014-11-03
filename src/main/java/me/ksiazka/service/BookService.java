package me.ksiazka.service;

import me.ksiazka.model.Book;

import java.util.List;

public interface BookService extends BasicServiceInterface<Book> {
    public List<Book> lastFiveBooksAdded(int page);
}
