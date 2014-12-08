package me.ksiazka.test.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import junit.framework.Assert;
import me.ksiazka.model.BookAlreadyExistingOnThisListException;
import me.ksiazka.model.User;
import me.ksiazka.model.UserRole;
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
import java.util.List;

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
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @Autowired
    @Qualifier("usersInDatabase")
    private Integer usersInDatabse;

    @Test
    public void getUserTest() {

        User user = userService.get(1);
        Assert.assertNotNull(user);
        Assert.assertEquals("Użytkownik usunięty", user.getUsername());
        Assert.assertEquals("test", user.getPassword());
    }

    @Test
    public void saveUserTest() {

        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");

        User user = new User();
        user.setName("Pawel");
        user.setSurname("Zygar");
        user.setUsername("PWZ");
        user.setEmail("pikus321@gmial.com");
        user.setPassword("131191"); //cannot be null
        user.getRoles().add(userRole);

        long id = userService.save(user);

        Assert.assertEquals("Zygar", userService.get(id).getSurname());
        Assert.assertEquals("ROLE_USER", userService.get(id).getRoles().get(0).getRole());
    }

    @Test
    public void getAllUserTest() {

        List<User> userList = userService.getAll();

        Assert.assertEquals((int) usersInDatabse, userList.size());
    }

    @Test
    public void updateUserTest() {

        User user = userService.get(2);
        Assert.assertFalse(user.getUsername().equals("Jonasz"));
        user.setUsername("Jonasz");
        userService.save(user);

        User retrivedUser = userService.get(2);
        Assert.assertEquals("Jonasz", retrivedUser.getUsername());
    }

    @Test
    @Ignore
    public void deleteUserTest() {

        /*
        @ToDo: Test do napisania po tym jak rozwiazane zostanie issue #11
         */
    }

    @Test
    @Ignore
    public void deleteFromHaveListTest() {

    }



}
