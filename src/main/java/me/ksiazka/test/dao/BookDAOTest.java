package me.ksiazka.test.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import junit.framework.Assert;
import me.ksiazka.dao.BookDAO;
import me.ksiazka.model.Book;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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
    SessionFactory session;

    @Test
    @DatabaseSetup("classpath:/testsDataset.xml")
    public void getBookTest() {

        Book book = bookDAO.getBook(1);
        Assert.assertEquals("Jarek Cimoch i Piwnica Tajemnic", book.getTitle());

    }


    @Test
    @DatabaseSetup("classpath:/testsDataset.xml")
    public void saveUserBookTest() {

        Book book = bookDAO.getBook(2);


        bookDAO.saveBook(book);
        bookDAO.testowySaveUserBook(book);

    }
}
