package me.ksiazka.test.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import junit.framework.Assert;
import me.ksiazka.dao.BookDAO;
import me.ksiazka.dao.OfferDAO;
import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.*;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml", "classpath:/spring-tests-cfg.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/testsDataset.xml")
@EnableTransactionManagement
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class BookDAOTest {

    @Autowired
    @Qualifier("booksInDatabase")
    private Integer booksInDatabase;
    @Autowired
    @Qualifier("usersInDatabase")
    private Integer usersInDatabse;

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OfferDAO offerDAO;

    @Test
    public void getBookTest() {

        Book book = bookDAO.get(4);
        Assert.assertEquals(4, (long) book.getId());
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

        long bookId = bookDAO.save(b);
        Book getted = bookDAO.get(bookId);

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

    //Test sprawdza usuniecie ksiazki i kaskadowe usuniecie z listy have i want.
    @Test
    //Usuwanie kaskadowe nie dziala
    public void deleteBookTest() {

        //Pobranie uzytkownika w celu ulatwienia testowania
        User user = userDAO.get(3);
        //Sprawdzamy czy ksiazka i jej kaskadowe zaleznosci istnieja w bazie
        Book book = bookDAO.get(4);
        Assert.assertNotNull(book);
        //Czy uzytkownik posiada ksiazke na have list
        Assert.assertEquals(user.getBookFromBooksHave(0).getBook().getId(),
                book.getId());
        //Czy uzytkownik posiada ksiazke na want list
        Assert.assertEquals(user.getBookFromBooksWant(1).getId(),
                book.getId());
        //Czy listy have i want maja odpowiednie dlugosci
        int haveListLength = user.getSizeOfBooksHave();
        int wantListLength = user.getSizeOfBooksWant();
        Assert.assertEquals(1, haveListLength);
        Assert.assertEquals(2, wantListLength);

        bookDAO.delete(book);

        Assert.assertEquals(null, bookDAO.get(4));

        //Czy ksiazka zostala usunieta kaskadowo z listy want
        Assert.assertEquals(1, user.getSizeOfBooksWant());
        //Czy na pewno usunieto dobra ksiazke
        Assert.assertEquals("XXX-Men Geneza: Jarek", user.getBookFromBooksWant(0).getTitle());
        //Czy usunieto ksiazke z listy have
        Assert.assertEquals(0, user.getSizeOfBooksHave());
        //Sprawdzamy, czy usunieto ksiazke z listy have innego uzytkownika
        Assert.assertEquals(1, userDAO.get(2).getSizeOfBooksHave());
        //Sprawdzamy, czy usunieto dobra ksiazke
        Assert.assertEquals("Jarek Cimoch i Piwnica Tajemnic",
                userDAO.get(2).getBookFromBooksHave(0).getBook().getTitle());

        /*
        @ToDo: Sprawdzenie czy usunieta ksiazka zostala odpowiednio podmieniona na zaleznych Offer
         */
    }


    @Test
    public void updateBookTest() {

        Book book = bookDAO.get(4);
        Assert.assertFalse(book.getPages()==410);
        book.setPages(410);
        book.setDescription("Best hard s-f ever made!");
        bookDAO.update(book);

        Assert.assertEquals((int) booksInDatabase, bookDAO.getAll().size());
        Assert.assertEquals(410, bookDAO.get(4).getPages());
        Assert.assertEquals("Best hard s-f ever made!", bookDAO.get(4).getDescription());
    }


    @Test
    public void findEachUserWithBookInHaveListTest() {

        List<User> list = bookDAO.findEachUserWithBookInHaveList(4);

        Assert.assertEquals(4, list.size());
        //Sprawdzamy, czy na pewno mamy tych uzytkownikow, ktorych chcemy
        Assert.assertTrue(
                (list.get(0).getName().equals("Caroslaw") && list.get(1).getName().equals("Jarke")) ||
                        (list.get(0).getName().equals("Jarke") && list.get(1).getName().equals("Caroslaw"))
        );
    }

    @Test
    public void findEachUserWithBookInWantListTest() {

        //Test analogiczny do testu wyzej
        List<User> list = bookDAO.findEachUserWithBookInWantList(4);

        Assert.assertEquals(1, list.size());
        Assert.assertTrue(list.get(0).getName().equals("Jarke"));
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Rollback(true)
    public void saveWithNullPropertyTest() {

        bookDAO.save(new Book());
    }

}
