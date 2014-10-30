package me.ksiazka.test.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import junit.framework.Assert;
import me.ksiazka.dao.BookDAO;
import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.Condition;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/testsDataset.xml")
public class UserDAOTest {

    @Autowired
    UserDAO userDAO;
    @Autowired
    BookDAO bookDAO;

    @Test
    public void addToHaveListTest() {

        int sizeOfHaveList = userDAO.getUserHaveList(2).size();
        long userBookId = userDAO.addToHaveList(userDAO.getUser(2), bookDAO.getBook(1), Condition.BAD);

        Assert.assertEquals(sizeOfHaveList+1, userDAO.getUserHaveList(2).size());

        UserBook userBook = userDAO.getUserBook(userBookId);
        Assert.assertEquals(2, (long) userBook.getUser().getId());
        Assert.assertEquals(1, (long) userBook.getBook().getId());
        Assert.assertEquals(Condition.BAD, userBook.getBookCondition());
    }

    @Test
    public void getUserBookTest() {

        UserBook userBook = userDAO.getUserBook(2);

        Assert.assertEquals(2, (long) userBook.getId());
        Assert.assertEquals(2, (long) userBook.getUser().getId());
        Assert.assertEquals(3, (long) userBook.getBook().getId());
    }

    @Test
    public void findUserByUsername() {
        User user = userDAO.findUserByUsername("Konasz");

        Assert.assertEquals(new Long(1), user.getId());
        Assert.assertEquals("Konasz", user.getUsername());
        Assert.assertEquals("jarke69@bdimension.org", user.getEmail());
        Assert.assertEquals("Caroslaw", user.getName());
        Assert.assertEquals("Jimoch", user.getSurname());
        Assert.assertEquals("tajne", user.getPassword());

        User nuser = userDAO.findUserByUsername("Nieistniejacy user");

        Assert.assertNull(nuser);
    }

    @Test
    public void findUserByEmail() {
        User user = userDAO.findUserByEmail("jarke69@bdimension.org");

        Assert.assertEquals(new Long(1), user.getId());
        Assert.assertEquals("Konasz", user.getUsername());
        Assert.assertEquals("jarke69@bdimension.org", user.getEmail());
        Assert.assertEquals("Caroslaw", user.getName());
        Assert.assertEquals("Jimoch", user.getSurname());
        Assert.assertEquals("tajne", user.getPassword());

        User nuser = userDAO.findUserByEmail("Jarkonosze@bdimension.pl");

        Assert.assertNull(nuser);
    }
}
