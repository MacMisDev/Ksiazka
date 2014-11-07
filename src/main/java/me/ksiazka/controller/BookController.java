package me.ksiazka.controller;


import me.ksiazka.model.Book;
import org.springframework.ui.Model;

public interface BookController {
    public String list(int page, Model model);
    public Book showBook(Book book, Long id);
    public String addBookToSystem(Model model);
    public Book addBookToSystem(Book book);
    public String deleteBook(Long bookId);
}
