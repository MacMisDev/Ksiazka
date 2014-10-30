//package me.ksiazka.test.dao;
//
//import com.github.springtestdbunit.DbUnitTestExecutionListener;
//import com.github.springtestdbunit.annotation.DatabaseSetup;
//import com.github.springtestdbunit.annotation.ExpectedDatabase;
//import junit.framework.Assert;
//import me.ksiazka.dao.BookDAO;
//import me.ksiazka.dao.UserDAO;
//import me.ksiazka.model.Condition;
//import me.ksiazka.model.User;
//import me.ksiazka.model.UserBook;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
//import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/spring-cfg.xml"})
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
//        DbUnitTestExecutionListener.class })
//@DatabaseSetup("classpath:/testsDataset.xml")
//@Transactional
//public class UserDAOTest {
//
//    @Autowired
//    @Qualifier("usersInDatabase")
//    Integer usersInDatabse;
//
//    @Autowired
//    UserDAO userDAO;
//    @Autowired
//    BookDAO bookDAO;
//
//    @Test
//    public void getUserTest() {
//
//        User user = userDAO.get(2);
//
//        Assert.assertEquals(2, (long) user.getId());
//        Assert.assertEquals("jarke@jarke.jr", user.getEmail());
//        Assert.assertEquals("Jarke", user.getName());
//        Assert.assertEquals("Cimoche", user.getSurname());
//
//    }
//
//    @Test
//    //Brak implementacji getAll
//    @Ignore
//    public void getAllUsersTest() {
//
//        Assert.assertEquals(usersInDatabse, userDAO.getAll());
//    }
//
//    @Test
//    //Brak implementacji saveUser
//    @Ignore
//    public void addUserTest() {
//
//        User user = new User();
//        user.setName("Wojtek");
//        user.setName("Nowak");
//        user.setEmail("wojtek@py.py");
//
//        long id = userDAO.save(user);
//
//        User retrivedUser = userDAO.get(id);
//        Assert.assertEquals("Wojtek", retrivedUser.getName());
//        Assert.assertEquals("Nowak", retrivedUser.getSurname());
//        Assert.assertEquals("Wojtek@py.py", retrivedUser.getEmail());
//    }
//
//    @Test
//    //Brak implementacji deleteUser
//    @Ignore
//    public void deleteUserTest() {
//
//        Assert.assertNotNull(userDAO.get(1));
//        //Assert.assertNotNull(userDAO.getUserBook(1));
//
//        //userDAO.delete(1);
//        Assert.assertNull(userDAO.get(1));
//        //Sprawdzamy, czy zostaly kaskadowo usuniete wpisy w tabeli UserBook
//        //Assert.assertNull(userDAO.getUserBook(1));
//    }
//
//    @Test
//    //Brak implementacji getAll
//    //Brak implementacji updateUser
//    @Ignore
//    public void updateUserTest() {
//
//       // User user = userDAO.getUser(2);
//      //  Assert.assertFalse(user.getEmail()=="wojtek.py");
//      //  user.setEmail("wojtek.py");
//      //  userDAO.updateUser(user.getId(), user);
//
//        Assert.assertEquals((int) usersInDatabse, userDAO.getAll().size());
//        //Assert.assertEquals("wojtek.py", userDAO.getUser(2).getEmail());
//    }
//
//    @Test
//    //Brak implementacji metody addToWantList
//    //Brak implementacji metody getFromWantList
//    //Nie jestem pewny co do nazwy tabeli laczacej booksWant, a potrzebuje jej
//    //zeby wpisac spodziewane wyniki do pliku addToHaveListExpected.xml -
//    //zrodlo potencjalnego bledu.
//    @Ignore
//    @ExpectedDatabase("classpath:/expectedDatasets/addToHaveListExpected.xml")
//    public void addToWantListTest() {
//
//        //userDAO.addToWantList(1, 3);
//        //userDAO.addToWantList(1, 2);
//    }
//
//    @Test
//    //Test do napisania po tym, jak przejdzie addToWantListTest z powodu powodow.
//    @Ignore
//    public void deleteFromWantListTest() {
//
//    }
//
//    @Test
//    public void adr() {
//
//        User user = userDAO.getUser(1);
//        System.out.println(user.getAddressList().size());
//    }
//
//    @Test
//    public void addToHaveListTest() {
//
//        int sizeOfHaveList = userDAO.getUserHaveList(2).size();
//        int lastIndexInHaveList = sizeOfHaveList - 1;
//        long userBookId = userDAO.addToHaveList(userDAO.getUser(2), bookDAO.getBook(1), Condition.BAD);
//
//        Assert.assertEquals(sizeOfHaveList+1, userDAO.getUserHaveList(2).size());
//        lastIndexInHaveList++;
//
//        //Pobranie ksiazki bezposrednio z tabeli
//        UserBook userBook = userDAO.getUserBook(userBookId);
//        Assert.assertEquals(2, (long) userBook.getUser().getId());
//        Assert.assertEquals(1, (long) userBook.getBook().getId());
//        Assert.assertEquals(Condition.BAD, userBook.getBookCondition());
//
//        //Pobranie listy have przez DAO
//        List<UserBook> userBookList = userDAO.getUserHaveList(2);
//        Assert.assertEquals(2, userBookList.size());
//        Assert.assertEquals("Jarek Cimoch i Piwnica Tajemnic",
//                userBookList.get(sizeOfHaveList).getBook().getTitle());
//    }
//
//    @Test
//    public void getUserBookTest() {
//
//        UserBook userBook = userDAO.getUserBook(2);
//
//        Assert.assertEquals(2, (long) userBook.getId());
//        Assert.assertEquals(2, (long) userBook.getUser().getId());
//        Assert.assertEquals(3, (long) userBook.getBook().getId());
//    }
//
//    @Test
//    public void findUserByUsername() {
//        User user = userDAO.findUserByUsername("Konasz");
//
//        Assert.assertEquals(new Long(1), user.getId());
//        Assert.assertEquals("Konasz", user.getUsername());
//        Assert.assertEquals("jarke69@bdimension.org", user.getEmail());
//        Assert.assertEquals("Caroslaw", user.getName());
//        Assert.assertEquals("Jimoch", user.getSurname());
//        Assert.assertEquals("tajne", user.getPassword());
//
//        User nuser = userDAO.findUserByUsername("Nieistniejacy user");
//
//        Assert.assertNull(nuser);
//    }
//
//    @Test
//    public void findUserByEmail() {
//        User user = userDAO.findUserByEmail("jarke69@bdimension.org");
//
//        Assert.assertEquals(new Long(1), user.getId());
//        Assert.assertEquals("Konasz", user.getUsername());
//        Assert.assertEquals("jarke69@bdimension.org", user.getEmail());
//        Assert.assertEquals("Caroslaw", user.getName());
//        Assert.assertEquals("Jimoch", user.getSurname());
//        Assert.assertEquals("tajne", user.getPassword());
//
//        User nuser = userDAO.findUserByEmail("Jarkonosze@bdimension.pl");
//
//        Assert.assertNull(nuser);
//    }
//}
