package me.ksiazka.test.service;

import me.ksiazka.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml"})
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void itsARunnableMethodNowFuckOffJunit() {

    }

}
