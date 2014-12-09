package me.ksiazka.controller;

import me.ksiazka.misc.BookLists;
import me.ksiazka.misc.PageNumbers;
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

/*    @Override
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "0", required = false) int page, Model model) {
        if(!bookService.checkPageNumberForPagination(page)){
            page = 0;
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("maxPages", bookService.checkMaxPagesLimit());
        model.addAttribute("lastBooks", bookService.lastBooksAdded(page));
        return "book/list";
    }*/

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
        return bookService.get(bookService.save(book));
    }

    @Override
    @RequestMapping(value = "/admin/book/list", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8" ,consumes = "application/json;charset=UTF-8")
    public Book deleteBook(Book book) {
        bookService.delete(book);
        return book;
    }
}
