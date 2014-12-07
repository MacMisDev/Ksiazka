package me.ksiazka.service;

import me.ksiazka.model.Book;
import me.ksiazka.model.UserBook;

import java.util.List;

public interface UserBookService extends BasicServiceInterface<UserBook> {
    public List<Book> getAllUserHaveBooks(long userId);
    public List<Book> getAllUserWantBooks(long userId);
    public int checkPagesMaxLimit(int listSize);
}
