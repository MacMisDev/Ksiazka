package me.ksiazka.test.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import junit.framework.Assert;
import me.ksiazka.dao.BookDAO;
import me.ksiazka.dao.UserBookDAO;
import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.Condition;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;
import me.ksiazka.model.UserRole;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
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
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/testsDataset.xml")
@Transactional
public class UserDAOTest {

    @Autowired
    @Qualifier("usersInDatabase")
    Integer usersInDatabse;

    @Autowired
    UserDAO userDAO;
    @Autowired
    BookDAO bookDAO;
    @Autowired
    UserBookDAO userBookDAO;

    @Test
    /*
    Test do rozbudowy o zaleznosci na listach
     */
    public void getUserTest() {

        User user = userDAO.get(2);

        Assert.assertEquals(2, (long) user.getId());
        Assert.assertEquals("jarke@jarke.jr", user.getEmail());
        Assert.assertEquals("Jarke", user.getName());
        Assert.assertEquals("Cimoche", user.getSurname());

    }

    @Test
    public void getAllUsersTest() {
        Assert.assertEquals((int) usersInDatabse, userDAO.getAll().size());
    }

    @Test
    public void addUserTest() {

        User user = new User();
        user.setName("Wojtek");
        user.setSurname("Nowak");
        user.setEmail("Wojtek@py.py");
        user.setPassword("wojtek.py"); //cannot be null

        long id = userDAO.save(user);

        User retrivedUser = userDAO.get(id);
        Assert.assertEquals("Wojtek", retrivedUser.getName());
        Assert.assertEquals("Nowak", retrivedUser.getSurname());
        Assert.assertEquals("Wojtek@py.py", retrivedUser.getEmail());
        Assert.assertEquals("wojtek.py", retrivedUser.getPassword());
    }

    @Test
    public void deleteUserTest() {

        Assert.assertNotNull(userDAO.get(1));
        Assert.assertNotNull(userBookDAO.getUserBooks(1));

        userDAO.delete(1);
        Assert.assertNull(userDAO.get(1));
        Assert.assertNull(userBookDAO.getUserBooks(1));
    }

    @Test
    //Brak implementacji getAll
    //Brak implementacji updateUser
    @Ignore
    public void updateUserTest() {

       User user = userDAO.get(2);
       Assert.assertFalse(user.getEmail() == "wojtek.py");
       user.setEmail("wojtek.py");
       userDAO.update(user);

        Assert.assertEquals((int) usersInDatabse, userDAO.getAll().size());
        Assert.assertEquals("wojtek.py", userDAO.get(2).getEmail());
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
