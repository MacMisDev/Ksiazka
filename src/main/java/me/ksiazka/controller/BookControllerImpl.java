package me.ksiazka.controller;

import me.ksiazka.model.Book;
import me.ksiazka.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("bookController")
@RequestMapping("/book")
public class BookControllerImpl implements BookController {

    @Autowired
    BookService bookService;

    @Override
    public String list(Model model) { return null; }

    @Override
    public String showBook(Long id, Model model) { return null; }

    @Override
    public String addBookToSystem(Model model) {
        return null;
    }

    @Override
    public String addedBookToSystem(Book book) {
        return null;
    }

    @Override
    public String deleteBook(Long bookId) {
        return null;
    }
}
