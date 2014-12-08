package me.ksiazka.test.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import junit.framework.Assert;
import me.ksiazka.dao.OfferDAO;
import me.ksiazka.model.Offer;
import me.ksiazka.model.OfferStatus;
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

import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml", "classpath:/spring-tests-cfg.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/testsDataset.xml")
@TransactionConfiguration(defaultRollback = false)
@Transactional
//Testy zostana napisane jak OfferDAO bedzie wymodelowane
public class OfferDAOTest {

    @Autowired
    OfferDAO offerDAO;


    @Autowired
    @Qualifier("offersInDatabase")
    private Integer offersInDatabse;

    @Test
    public void getOfferTest() {

        Offer offer = offerDAO.get(new Long(1));

        Assert.assertNotNull(offer);
        Assert.assertEquals(new Long(1), offer.getId());

    }

    @Test
    public void getAllOffersTest() {

        List<Offer> offers = offerDAO.getAll();

        Assert.assertEquals((int) offersInDatabse, offers.size());

    }

    @Test
    public void saveOfferTest() {

        Offer offer = new Offer();
        offer.setOfferStatus(OfferStatus.PENDING);

        Long id = offerDAO.save(offer);

        Offer fetchedBook = offerDAO.get(id);
        Assert.assertNotNull(fetchedBook);
        Assert.assertEquals(fetchedBook.getOfferStatus(), offer.getOfferStatus());


    }

    @Test
    public void updateOfferTest() {

        Offer offer = offerDAO.get(2);
        OfferStatus currentOfferStatus = offer.getOfferStatus();
        OfferStatus newOfferStatus = OfferStatus.CONFIRMED;

        //Sprawdzamy, czy są różne żeby nie zmienić przez przypadek na ten sam status
        Assert.assertNotSame(currentOfferStatus, newOfferStatus);

        offer.setOfferStatus(newOfferStatus);
        offerDAO.update(offer);

        Assert.assertEquals(newOfferStatus, offerDAO.get(2).getOfferStatus());

    }

    @Test
    public void deleteOfferTestObject() {

        Offer offer = offerDAO.get(3);
        int sizeBefore = offersInDatabse;

        offerDAO.delete(offer);

        Assert.assertEquals(sizeBefore-1, offerDAO.getAll().size());
        Assert.assertNull(offerDAO.get(3));
    }

    @Test
    public void deleteOfferTestId() {

        Long id = new Long(3);
        Offer offer = offerDAO.get(id);
        int sizeBefore = offersInDatabse;

        offerDAO.delete(id);

        Assert.assertEquals(sizeBefore-1, offerDAO.getAll().size());
        Assert.assertNull(offerDAO.get(3));

    }

}
