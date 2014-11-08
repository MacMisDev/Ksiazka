package me.ksiazka.controller;

import me.ksiazka.model.Book;
import me.ksiazka.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "0", required = false) int page, Model model) {
        if(!bookService.checkPageNumberForPagination(page)){
            page = 0;
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("maxPages", bookService.checkMaxPagesLimit());
        model.addAttribute("lastBooks", bookService.lastBooksAdded(page));
        return "book/list";
    }

    @Override
    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Book showBook(@PathVariable Long bookId) {
        return bookService.get(bookId);
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addBookToSystem(Model model) {
        model.addAttribute("book", new Book());
        return "book/newBook";
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Book addBookToSystem(@RequestBody Book book) {
        return bookService.get(bookService.save(book));
    }

    @Override
    @RequestMapping(value = "/admin/book/list", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book deleteBook(Book book) {
        bookService.delete(book);
        return book;
    }
}
