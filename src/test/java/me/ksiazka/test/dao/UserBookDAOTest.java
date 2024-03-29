package me.ksiazka.test.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import junit.framework.Assert;
import me.ksiazka.dao.BookDAO;
import me.ksiazka.dao.UserBookDAO;
import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.Book;
import me.ksiazka.model.Condition;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml", "classpath:/spring-tests-cfg.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/testsDataset.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class UserBookDAOTest {

    @Autowired
    @Qualifier("userBooksInDatabase")
    private Integer userBooksInDatabase;

    @Autowired
    private UserBookDAO userBookDAO;
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private UserDAO userDAO;

    @Test
    public void getUserBookTest() {

        UserBook userBook = userBookDAO.get(3);

        Assert.assertNotNull(userBook);
        Assert.assertEquals(2, (long) userBook.getUser().getId());
        Assert.assertEquals("Caroslaw", userBook.getUser().getName());
        Assert.assertEquals(4, (long) userBook.getBook().getId());
        Assert.assertEquals("Ślepowidzenie", userBook.getBook().getTitle());
    }

    @Test
    public void getAllUserBookTest() {

        Assert.assertEquals((int) userBooksInDatabase, userBookDAO.getAll().size());
    }


    @Test
    public void getAllUserWantBooksTest() {

        User u = userDAO.get(4);
        List<Book> list = userBookDAO.getAllUserWantBooks(u.getId());
        Assert.assertEquals(3, list.size());

    }

    @Test
    public void saveUserBookTest() {

        UserBook userBook = new UserBook();
        userBook.setBook(bookDAO.get(2));
        userBook.setUser(userDAO.get(3));
        userBook.setBookCondition(Condition.BAD);
        long id = userBookDAO.save(userBook);

        UserBook retrivedUserBook = userBookDAO.get(id);
        Assert.assertEquals(2, (long) retrivedUserBook.getBook().getId());
        Assert.assertEquals(3, (long) retrivedUserBook.getUser().getId());
        Assert.assertEquals(Condition.BAD, retrivedUserBook.getBookCondition());
        Assert.assertEquals(userBooksInDatabase+1, userBookDAO.getAll().size());

    }

    @Test
    public void updateUserBookTest() {

        UserBook userBook = userBookDAO.get(2);
        Assert.assertNotNull(userBook);
        Assert.assertFalse(userBook.getBookCondition().equals(Condition.BAD));

        userBook.setBookCondition(Condition.BAD);
        userBookDAO.update(userBook);

        UserBook userBookAfterUpdate = userBookDAO.get(2);
        Assert.assertEquals(Condition.BAD, userBookAfterUpdate.getBookCondition());
        Assert.assertEquals((int) userBooksInDatabase, userBookDAO.getAll().size());
    }

    @Test
    public void deleteUserBookTest() {

        Assert.assertNotNull(userBookDAO.get(3));
        userBookDAO.delete(3);
        Assert.assertNull(userBookDAO.get(3));
        Assert.assertEquals((int) userBooksInDatabase-1, userBookDAO.getAll().size());
    }
}
