package me.ksiazka.test.service;

import junit.framework.Assert;
import me.ksiazka.dao.BookDAO;
import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.Book;
import me.ksiazka.model.BookStatus;
import me.ksiazka.model.User;
import me.ksiazka.service.SearchService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml"})
@EnableTransactionManagement
@TransactionConfiguration(defaultRollback = false)
public class SearchingServiceTest {

    @Autowired
    SearchService searchService;
    @Autowired
    UserDAO userDAO;
    @Autowired
    BookDAO bookDAO;
    //Needed for creating explicite transaction
    @Autowired
    SessionFactory sessionFactory;

    private long saved1id;
    private long saved2id;
    private long saved3id;

    private static boolean doneBefore = false;

    //@Before
    @Transactional
    public void setupDatabase() {

        if(!doneBefore) {

            //FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
            //Transaction tx = fullTextSession.getTransaction();
            //Session session = sessionFactory.getCurrentSession();
            //Transaction tx = session.getTransaction();
            //tx.begin();

            System.out.println("setup");

            User u1 = new User("Maciej", "Miś", "kolak122", "macmickoks@gmial.com", "MacMis");
            User u2 = new User("Łukasz", "Skarżyński", "kolak12", "lukskarkoks@gmail.com", "Konio");
            User u3 = new User("Krzysztof", "RusekNiePomoglMiZHQLem", "hql", "krzys@gmail.com", "Krzys");

            Book b1 = new Book("Krzysiu i magiczne grzybki", "12345", "Konio", "Konio", "Ksiazeczka o Krzysiu",
                    2014, 25, BookStatus.AWAITING);
            Book b2 = new Book("Jarek i przygody w ciemnym lesie", "6789", "Konio", "Konio", "Ksiazeczka o Jarku",
                    2013, 120, BookStatus.AWAITING);
            Book b3 = new Book("Jarek i przygody na dzikiej plazy", "1358", "Mis", "Mis", "Ksiazeczka o Jarku 2",
                    2012, 89, BookStatus.AWAITING);

            saved1id = userDAO.save(u1);
            saved2id = userDAO.save(u2);
            saved3id = userDAO.save(u3);

            /*bookDAO.save(b1);
            bookDAO.save(b2);
            bookDAO.save(b3);*/

            //fullTextSession.index(fullTextSession.load(User.class, saved1id));
            //fullTextSession.index(fullTextSession.load(User.class, saved2id));
            //fullTextSession.index(fullTextSession.load(User.class, saved3id));
            //tx.commit();
            //session.close();

//            try {
//                searchService.reindex();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            try {
                searchService.reindex();
            } catch (InterruptedException e) {

                System.out.println("--------------------------------------------------");
                System.out.println("--------------------------------------------------");
                System.out.println("SearchingServiceTest failed to reindex database");
                System.out.println("--------------------------------------------------");
                System.out.println("--------------------------------------------------");
                e.printStackTrace();
            }
            doneBefore = true;
        }

    }

    @Test
    //@Repeat(3)
    @Transactional
    @Rollback(false)
    @Ignore
    public void searchByEmailTest() throws InterruptedException {

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.getTransaction();
//        tx.begin();
//        searchService.reindex();
//        User uu = new User("zjjppppaaaa9999", "sdpppaaaja9999", "xxpppaa999jz", "a@o999p", "zzjpppp999px");

           // sessionFactory.getCurrentSession().saveOrUpdate(uu);

//        tx.commit();

        //Assert.assertNotNull(userDAO.get(1));

        //userDAO.save(new User("Magdalena", "Hara", "hara", "hara@gmial.com", "Hara"));

        try {

            searchService.reindex();
            System.out.println("omg");

        } catch (InterruptedException e) {

            System.out.println("--------------------------------------------------");
            System.out.println("--------------------------------------------------");
            System.out.println("SearchingServiceTest failed to reindex database");
            System.out.println("--------------------------------------------------");
            System.out.println("--------------------------------------------------");
            e.printStackTrace();
        }

        //userDAO.save(new User("Magdalena", "Hara", "hara", "hara@gmial.com", "Hara"));

        List<User> u = null;
        try {
            u = searchService.searchByEmail("krzys@gmial.com");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(":: " + u.size());
        for(User user : u) {
            System.out.println(":: " + u.get(0).getName());


        }
    }
}
