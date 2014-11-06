package me.ksiazka.test.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import me.ksiazka.dao.OfferDAO;
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

import javax.transaction.Transactional;

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

    @Test
    @Ignore
    public void getOfferTest() {

    }

    @Test
    @Ignore
    public void getAllOffersTests() {

    }

    @Test
    @Ignore
    public void saveOfferTest() {

    }

    @Test
    @Ignore
    public void updateOfferTest() {

    }

    @Test
    @Ignore
    public void deleteOfferTest() {

    }

}
