package me.ksiazka.test.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import junit.framework.Assert;
import me.ksiazka.dao.BookDAO;
import me.ksiazka.dao.UserBookDAO;
import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.*;
import org.hibernate.exception.ConstraintViolationException;
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
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml", "classpath:/spring-tests-cfg.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/testsDataset.xml")
@EnableTransactionManagement
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class UserDAOTest {

    @Autowired
    @Qualifier("usersInDatabase")
    private Integer usersInDatabse;

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserBookDAO userBookDAO;

    @Test
    public void getUserTest() {

        User user = userDAO.get(2);

        Assert.assertEquals(2, (long) user.getId());
        Assert.assertEquals("jarke@jarke.jr", user.getEmail());
        Assert.assertEquals("Jarke", user.getName());
        Assert.assertEquals("Cimoche", user.getSurname());
        Assert.assertEquals("ChybaTy", user.getUsername());
        Assert.assertEquals(1, user.getBooksHave().size());
        Assert.assertEquals(2, user.getBooksWant().size());
        //Dla pewnosci sprawdzamy czy np. na liscie books have znajduje
        //sie odpowiednia ksiazka
        Assert.assertEquals("Ślepowidzenie", user.getBooksHave().get(0).getBook().getTitle());

    }

    @Test
    //Brak implementacji getAll
    @Ignore
    public void getAllUsersTest() {

        Assert.assertEquals((int) usersInDatabse, userDAO.getAll().size());
    }

    @Test
    public void addUserTest() {

        User user = new User();
        user.setName("Wojtek");
        user.setSurname("Nowak");
        user.setUsername("wojtekN");
        user.setEmail("Wojtek@py.py lolada heldan");
        user.setPassword("wojtek.py"); //cannot be null

        long id = userDAO.save(user);

        User retrivedUser = userDAO.get(id);
        Assert.assertEquals("Wojtek", retrivedUser.getName());
        Assert.assertEquals("Nowak", retrivedUser.getSurname());
        Assert.assertEquals("wojtekN", retrivedUser.getUsername());
        Assert.assertEquals("Wojtek@py.py lolada heldan", retrivedUser.getEmail());
        Assert.assertEquals("wojtek.py", retrivedUser.getPassword());
    }

    @Test
    //Brak implementacji deleteUser
    @Ignore
    public void deleteUserTest() {

        //Porzadny opis testu specjalnie dla Krzysia bo nie kuma i robi mi
        //gowno-metody w dao.
        //Najpierw sprawdzamy czy uzytkownik, ktorego chcemy usunac w ogole
        //istnieje w bazie.
        Assert.assertNotNull(userDAO.get(1));
        //Potem przygotowujemy sie do sprawdzenia dzialania kaskadowego usuwania.
        //Sprawdzamy czy ilosc ksiazek na liscie have sie zgadza, powinny byc 2
        Assert.assertEquals(2, userDAO.get(1).getBooksHave().size());

        //Teraz usuwamy
        userDAO.delete(1);
        //Sprawdzamy czy uzytkownik zostal usuniety
        Assert.assertNull(userDAO.get(1));
        //Uzytkownik posiadal w swojej liscie have ksiazki z encji UserBook o id 1 i 3
        //Sprawdzamy czy sie usunely.
        Assert.assertNull(userBookDAO.get(1));
        Assert.assertNull(userBookDAO.get(3));
        //Dla pewnosci sprawdzamy, czy w bazie pozostal wpis w UserBook o id 2
        Assert.assertNotNull(userBookDAO.get(2));

        //Tutaj mozna dopisac sprawdzenie czy usunely sie wpisy w bazie z listy want
        //Obiekt uzytkownika naturalnie nie posiada tych wpisow, jako ze nie istnieje
        //Ale to jak najpierw przejdzie test w takiej formie jakiej jest.
    }

    @Test
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

    @Test(expected = ConstraintViolationException.class)
    public void saveWithNullPropertyTest() {

        userDAO.save(new User());
    }

    @Test
    //Test do przeniesienia w inna klase z @Before.
    //WAZNE! Niekompatybilny z @DatabaseSetup
    @Ignore
    public void searchByEmailTest() {

        List<User> u = null;
        try {
            u = userDAO.searchByEmail("jarke@jarke.jr");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(":: " + u.size());
        System.out.println(":: " + u.get(0).getName());

    }
}
