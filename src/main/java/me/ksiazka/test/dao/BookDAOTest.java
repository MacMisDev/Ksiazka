package me.ksiazka.test.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import junit.framework.Assert;
import me.ksiazka.dao.BookDAO;
import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class BookDAOTest {

    @Autowired
    BookDAO bookDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    SessionFactory session;

    @Test
    @DatabaseSetup("classpath:/testsDataset.xml")
    public void getBookTest() {

        Book book = bookDAO.getBook(1);
        Assert.assertEquals("Jarek Cimoch i Piwnica Tajemnic", book.getTitle());

    }

    @Test
    @Ignore
    @DatabaseSetup("classpath:/testsDataset.xml")
    public void haveListAdditionTest_proposal1() {

        /*
        W tym rozwiazaniu pobieramy uzytkownika i ksiazke,
        ktora chce on dodac do swojej listy, nastepnie
        obiekt uzytkownika dokonuje dodania, a dao update'uje
        uzytkownika, zapisujac go z nowa ksiazka na liscie.
         */
        User user = userDAO.getUser(2);
        Book book = bookDAO.getBook(1);
        //user.addToHaveList(book, Condition.GOOD);
        userDAO.updateUser(2, user);
    }

    @Test
    @Ignore
    @DatabaseSetup("classpath:/testsDataset.xml")
    public void haveListAdditionTest_proposal2() {

        /*
        W tym rozwiazaniu ktores DAO (w tym przykladzie usera)
        zawiera w sobie metode do bezposredniego przypisania
        ksiazki do uzytkownika (najlpeiej gdyby bylo to jakies named
        query w warstwie hibernate'owej).
         */
        //userDAO.addToHaveList(2, bookDAO.getBook(1), Condition.GOOD);
    }

    @Test
    @DatabaseSetup("classpath:/testsDataset.xml")
    public void saveUserBookTest() {

        Book book = bookDAO.getBook(2);


        bookDAO.saveBook(book);
        bookDAO.testowySaveUserBook(book);

    }

    @Test
    @DatabaseSetup("classpath:/testsDataset.xml")
    public void test() {
        Book book = bookDAO.getBook(1);
        bookDAO.testowySaveUserBook(book);
    }
}
