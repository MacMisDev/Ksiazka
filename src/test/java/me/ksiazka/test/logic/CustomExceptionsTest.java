package me.ksiazka.test.logic;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import me.ksiazka.model.BookAlreadyExistingOnThisListException;
import me.ksiazka.model.User;
import me.ksiazka.service.BookService;
import me.ksiazka.service.UserBookService;
import me.ksiazka.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CustomExceptionsTest {

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @Test(expected = BookAlreadyExistingOnThisListException.class)
    public void addToWantListExistingTest() throws BookAlreadyExistingOnThisListException {

        User user = userService.get(3);

        user.addToWantList(bookService.get(2));
        user.addToWantList(bookService.get(2));
    }

    @Test(expected = BookAlreadyExistingOnThisListException.class)
    public void addToHaveListExistingTest() throws BookAlreadyExistingOnThisListException {

        User user = userService.get(2);

        user.addToHaveList(user.getBookFromBooksHave(0));
    }
}
