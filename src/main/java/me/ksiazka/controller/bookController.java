package me.ksiazka.controller;


import me.ksiazka.model.Book;
import org.springframework.ui.Model;

public interface bookController {
    public String addBookToSystem(Model model);
    public String addedBookToSystem(Book book);
    public String deleteBook(Long bookId);
}
