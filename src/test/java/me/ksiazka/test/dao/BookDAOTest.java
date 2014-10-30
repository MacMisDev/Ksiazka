package me.ksiazka.test.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import junit.framework.Assert;
import me.ksiazka.dao.BookDAO;
import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/testsDataset.xml")
public class BookDAOTest {

    @Autowired
    Integer booksInDatabase;

    @Autowired
    BookDAO bookDAO;
    @Autowired
    UserDAO userDAO;

    @Test
    public void getBookTest() {

        Book book = bookDAO.getBook(3);
        Assert.assertEquals(3, (long) book.getId());
        Assert.assertEquals("Ślepowidzenie", book.getTitle());
        Assert.assertEquals("9788374802932", book.getISBN());
        Assert.assertEquals("Peter Watts", book.getAuthor());
        Assert.assertEquals("MAG", book.getPublisher());
        Assert.assertEquals("Hard-sf", book.getDescription());
        Assert.assertEquals(2013, book.getPublicationYear());
        Assert.assertEquals(408, book.getPages());
        Assert.assertEquals(BookStatus.ACCEPTED, book.getBookStatus());
    }

    @Test
    public void getAllBooksTest() {

        List<Book> allBooks = bookDAO.getAll();
        Assert.assertEquals((int) booksInDatabase, allBooks.size());

    }


    @Test
    public void addBookTest() {

        Book b = new Book();
        b.setTitle("Inne Pieśni");
        b.setISBN("9788308042267");
        b.setAuthor("Jacek Dukaj");
        b.setPublisher("Wydawnictwo Literackie");
        b.setDescription("Nie science fiction, nie fantasy, nie historia alternatywna lecz rzecz dzieje się w innej Europie, " +
                "w innym świecie, gdzie prawa rządzące rzeczywistością bliższe są domysłom Arystotelesa na temat Formy i Materii, " +
                "aniżeli teoriom Newtona i Einsteina pisał o Innych pieśniach Jacek Dukaj. " +
                "Ten niezwykły obraz świata rządzonego przez Formę, zbudowanego z pięciu żywiołów, podporządkowanego teoriom " +
                "Empedoklesa i Arystotelesa, w którym nowożytna nauka jako pogląd z gruntu fałszywy nigdy się nie rozwinęła, " +
                "przyniósł autorowi aż trzy prestiżowe nagrody. Książka do czasu premiery Lodu uznawana była przez krytyków " +
                "i czytelników za najlepszą powieść w jego dorobku.\n" +
                "\n" +
                "Nagroda Fandomu Polskiego im. Janusza A. Zajdla za rok 2003. ");
        b.setPublicationYear(2008);
        b.setPages(530);

        long bookId = bookDAO.saveBook(b);
        Book getted = bookDAO.getBook(bookId);

        Assert.assertEquals(bookId, (long) getted.getId());
        Assert.assertEquals(b.getTitle(), getted.getTitle());
        Assert.assertEquals(b.getISBN(), getted.getISBN());
        Assert.assertEquals(b.getAuthor(), getted.getAuthor());
        Assert.assertEquals(b.getPublisher(), getted.getPublisher());
        Assert.assertEquals(b.getDescription(), getted.getDescription());
        Assert.assertEquals(b.getPublicationYear(), getted.getPublicationYear());
        Assert.assertEquals(b.getPages(), getted.getPages());
        Assert.assertEquals(BookStatus.AWAITING, getted.getBookStatus());
    }

    @Ignore
    @Test
    public void addBookWithNotNullablePropsTest() {
        //Test zostanie napisany po tym jak addBookTest()
        //bedzie przechodzil oraz jak rozwiazany zostanie issue #2
    }

    //Test do rozbudowy, aczkolwiek ta wersja juz moze sluzyc do testowania
    @Test
    public void deleteBookTest() {

        Book book = bookDAO.getBook(3);
        Assert.assertNotNull(book);
        UserBook userBook = userDAO.getUserBook(2);
        //Sprawdzamy czy ksiazki na pewno sa powiazane
        Assert.assertEquals(userBook.getBook().getId(), book.getId());

        bookDAO.deleteBook(3);

        Assert.assertEquals(null, bookDAO.getBook(3));
        Assert.assertEquals(booksInDatabase-1, bookDAO.getAll().size());
        Assert.assertEquals(null, userDAO.getUserBook(2));
    }


    @Test
    public void updateBookTest() {

        Book book = bookDAO.getBook(3);
        book.setPages(410);
        book.setDescription("Best hard s-f ever made!");
        bookDAO.updateBook(book);

        Assert.assertEquals((int) booksInDatabase, bookDAO.getAll().size());
        Assert.assertEquals(410, bookDAO.getBook(3).getPages());
        Assert.assertEquals("Best hard s-f ever made!", bookDAO.getBook(3).getDescription());

    }

}
