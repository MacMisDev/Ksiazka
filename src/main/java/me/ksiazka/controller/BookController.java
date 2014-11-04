package me.ksiazka.controller;


import me.ksiazka.model.Book;
import org.springframework.ui.Model;

public interface BookController {
    public String list(int page, Model model);
    public String showBook(Long id, Model model);
    public String addBookToSystem(Model model);
    public Book addBookToSystem(Book book);
    public String deleteBook(Long bookId);
}
