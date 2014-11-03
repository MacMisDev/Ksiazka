package me.ksiazka.test.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import junit.framework.Assert;
import me.ksiazka.dao.BookDAO;
import me.ksiazka.dao.UserBookDAO;
import me.ksiazka.dao.UserDAO;
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
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml", "classpath:/spring-tests-cfg.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/testsDataset.xml")
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
        Assert.assertEquals(1, (long) userBook.getUser().getId());
        Assert.assertEquals("Caroslaw", userBook.getUser().getName());
        Assert.assertEquals(3, (long) userBook.getBook().getId());
        Assert.assertEquals("Ślepowidzenie", userBook.getBook().getTitle());
    }

    @Test
    public void getAllUserBookTest() {

        Assert.assertEquals((int) userBooksInDatabase, userBookDAO.getAll().size());
    }

    @Test
    public void saveUserBookTest() {

        UserBook userBook = new UserBook();
        userBook.setBook(bookDAO.get(1));
        userBook.setUser(userDAO.get(2));
        userBook.setBookCondition(Condition.BAD);
        long id = userBookDAO.save(userBook);

        UserBook retrivedUserBook = userBookDAO.get(id);
        Assert.assertEquals(1, (long) retrivedUserBook.getBook().getId());
        Assert.assertEquals(2, (long) retrivedUserBook.getUser().getId());
        Assert.assertEquals(Condition.BAD, retrivedUserBook.getBookCondition());
        Assert.assertEquals(userBooksInDatabase+1, userBookDAO.getAll().size());

    }

    @Test
    public void updateUserBookTest() {

        UserBook userBook = userBookDAO.get(1);
        Assert.assertNotNull(userBook);
        Assert.assertFalse(userBook.getBookCondition().equals(Condition.BAD));

        userBook.setBookCondition(Condition.BAD);
        userBookDAO.update(userBook);

        UserBook userBookAfterUpdate = userBookDAO.get(1);
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
