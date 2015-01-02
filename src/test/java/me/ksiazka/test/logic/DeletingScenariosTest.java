package me.ksiazka.test.logic;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import junit.framework.Assert;
import me.ksiazka.model.Offer;
import me.ksiazka.model.OfferStatus;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;
import me.ksiazka.service.OfferService;
import me.ksiazka.service.UserService;
import org.junit.Ignore;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml", "classpath:/spring-tests-cfg.xml"})
@Transactional
@DatabaseSetup("classpath:/deletingScenariosDataset.xml")
@EnableTransactionManagement
@TransactionConfiguration(defaultRollback = false)
public class DeletingScenariosTest {

    @Autowired
    private UserService userService;
    @Autowired
    private OfferService offerService;

    /**
     * @author Konio
     */
    @Test
    public void modelIntegrityTest() {

        //Wywolywany tylko w celu sprawdzenia, czy dataset zostal zbudowany poprawnie
        //z punktu widzenia SQLa.
    }

    /**
     * @author Konio
     */
    @Test
    public void relizeOfferTest() {

        //Test symulujacy proces akceptacji zlozonej oferty.
    }

    /**
     * @author Konio
     */
    @Test
    public void newOfferTest() {

        //Test symulujacy skladanie nowej oferty

        //Uzytkownik C sklada oferte uzytkownikowi A - chce oddac
        //ksiazke c1 w zamian za ksiazke a2.
        //(1) Pobieramy uzytkownikow bioracych udzial w wymianie.
        User A = userService.get(2);
        User C = userService.get(4);
        //(2) Przygotowujemy listy ksiazek, ktore chca oni wymienic
        //w tej konkretnej ofercie.
        List<UserBook> offeredBooks = new ArrayList<UserBook>();
        offeredBooks.add(C.getBookFromBooksHave(0));
        //Asercja dla pewnosci:
        Assert.assertEquals("c1", C.getBookFromBooksHave(0).getBook().getTitle());
        List<UserBook> wantedBooks = new ArrayList<UserBook>();
        wantedBooks.add(A.getBookFromBooksHave(1));

        //Asercja dla pewnosci
        Assert.assertEquals("a2", A.getBookFromBooksHave(1).getBook().getTitle());
        //(3) Przygotowujemy i wysylamy do bazy oferte
        // ---- do tego miejsca test przechodzi, bo sprawdzalem, dalej brakuje metod:
        // * offerService.prepareNewOffer
        // * offerService.getOfferedUser
        // * offerService.getOfferingUser
        // Ja juz nie kumam jak te relacje maja dzialac z poziomu javy, wiec musisz
        // te metody Krzysiu zaimplementowac. Czy zrobisz to pure java, czy jakims
        // wywolaniem NamedQuery to juz mi nie robi, ma dzialac.
        Offer offer = offerService.prepareNewOffer(C, A, offeredBooks, wantedBooks);
        long id = offerService.save(offer);

        //Asercje sprawdzajace poprawnosc oferty
        Offer gettedOffer = offerService.get(id);
        //Sprawdzamy, czy oferta w ogole istnieje
        Assert.assertNotNull(gettedOffer);
        //Sprawdzamy, czy oferta w czasie tworzenia otrzymala odpowiedni status
        Assert.assertEquals(OfferStatus.PENDING, gettedOffer.getOfferStatus());
        //Sprawdzamy, czy uzytkownicy zostali odpowiednio przypisani
        Assert.assertEquals("C", offerService.getOfferingUser(gettedOffer).getUsername());
        Assert.assertEquals("A", offerService.getOfferedUser(gettedOffer).getUsername());
        //Sprawdzamy, czy ksiazki znalazly sie na odpowiednich listach
        Assert.assertEquals("c1", offer.getOfferedBooks().get(0).getBook().getTitle());
        Assert.assertEquals("a2", offer.getWantedBooks().get(0).getBook().getTitle());
    }

    /**
     * @author Konio
     */
    @Test
    public void changeOfferTest() {

        //Test symulujacy zmiane otrzymanej oferty.
    }

    /**
     * @author Konio
     */
    @Test
    public void rejectOfferTest() {

        //Test symulujacy odrzucenie otrzymanej oferty.
    }

    /**
     * @author Konio
     */
    @Test
    public void userDeletedTest() {

        //Test symulujacy usuniecie uzytkownika po tym, jak zakonczono wymiane.
    }

    /**
     * @author Konio
     */
    @Test
    public void bookDeletedTest() {

        //Test symulujacy usuniecie ksiazki po tym, jak zakonczono wymiane.
    }

    /**
     * @author Konio
     */
    @Test
    public void bothUsersDeletedTest() {

        //Test symulujacy usuniecie obu uzytkownikow po tym, jak zakonczono wymiane.
    }

    /**
     * @author Konio
     */
    @Test
    public void bookOrUserDeletedBeforeAcceptedTest() {

        //Usuniecie ksiazki, a nastepnie uzytkownika, oba wydarzenia majace miejsce
        //ZANIM zaakceptowano oferte. Chwilowo nie pisze tego testu, jako ze nie mamy
        //jeszcze systemu powiadamiania o tego typu rzeczach.
    }

}
