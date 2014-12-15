package me.ksiazka.controller;

import me.ksiazka.Wrapper.BookLists;
import me.ksiazka.Wrapper.PageNumbers;
import me.ksiazka.model.Book;
import me.ksiazka.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller("bookController")
@RequestMapping("/book")
public class BookControllerImpl implements BookController {

    @Autowired
    private BookService bookService;

    @Override
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String showBookHome() {
        return "redirect:/book/list";
    }

    @Override
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody BookLists list() {
        return bookService.bookLists(0,0);
    }

    @Override
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public @ResponseBody BookLists list(@RequestBody PageNumbers pageNumbers) {
        return bookService.bookLists(pageNumbers.getLastBooksAddedPage(), pageNumbers.getMostPopularBooksPage());
    }

    @Override
    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody Book showBook(@PathVariable long bookId) {
        return bookService.get(bookId);
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addBookToSystem(Model model) {
        model.addAttribute("book", new Book());
        return "book/newBook";
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public @ResponseBody Book addBookToSystem(@RequestBody Book book) {
        //todo fix

        return bookService.get(bookService.save(book));
    }

    @Override
    @RequestMapping(value = "/admin/list", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8" ,consumes = "application/json;charset=UTF-8")
    public Book deleteBook(Book book) {
        bookService.delete(book);
        return book;
    }
}
