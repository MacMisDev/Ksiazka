package me.ksiazka.controller;


import me.ksiazka.model.Book;
import org.springframework.ui.Model;

public interface BookController {
    public String showBookHome();
    public String list(int page, Model model);
    public Book showBook(Long id);
    public String addBookToSystem(Model model);
    public Book addBookToSystem(Book book);
    public Book deleteBook(Book book);
}
