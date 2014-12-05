package me.ksiazka.test.logic;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import junit.framework.Assert;
import me.ksiazka.model.BookAlreadyExistingOnThisListException;
import me.ksiazka.model.User;
import me.ksiazka.service.BookService;
import me.ksiazka.service.UserService;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml", "classpath:/spring-tests-cfg.xml"})
@Transactional
@DatabaseSetup("classpath:/testsDataset.xml")
@EnableTransactionManagement
@TransactionConfiguration(defaultRollback = false)
public class OnModelLogicTest {

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @Autowired
    @Qualifier("usersInDatabase")
    private Integer usersInDatabse;

    @Test
    public void addToWantListTest() throws BookAlreadyExistingOnThisListException {

        User user = userService.get(3);
        Assert.assertEquals(2, user.getSizeOfBooksWant());

        user.addToWantList(bookService.get(2));

        User retrievedUser = userService.get(3);
        Assert.assertEquals(3, retrievedUser.getSizeOfBooksWant());
        Assert.assertEquals("Jarek Cimoch i Piwnica Tajemnic",
                retrievedUser.getBookFromBooksWant(2).getTitle());
    }

    @Test
    public void deleteFromWantListTest() {

        User user = userService.get(3);
        Assert.assertEquals(2, user.getSizeOfBooksWant());

        user.removeFromWantList(0);

        User retrivedUser = userService.get(3);
        Assert.assertEquals(1, user.getSizeOfBooksWant());
        Assert.assertEquals("Ślepowidzenie",
                retrivedUser.getBookFromBooksWant(0).getTitle());
    }
}
