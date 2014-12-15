package me.ksiazka.controller;


import me.ksiazka.Wrapper.BookLists;
import me.ksiazka.Wrapper.PageNumbers;
import me.ksiazka.model.Book;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface BookController {
    public String showBookHome();
    public BookLists list();
    public BookLists list(PageNumbers pageNumbers);
    public Book showBook(long id);
    public String addBookToSystem(Model model);
    public Book addBookToSystem(Book book);
    public Book deleteBook(Book book);
}
