package me.ksiazka.test.general;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml"})
public class GeneralConfigurationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void applicationContextTest() {

        Assert.assertNotNull(applicationContext);
    }
}
