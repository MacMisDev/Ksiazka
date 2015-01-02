package me.ksiazka.test.controller;

import junit.framework.Assert;
import me.ksiazka.controller.UserController;
import me.ksiazka.model.User;
import me.ksiazka.model.UserRole;
import me.ksiazka.service.UserBookService;
import me.ksiazka.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-cfg.xml", "classpath:/spring-tests-cfg.xml"})
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userServiceMock;

    @Mock
    private UserBookService userBookServiceMock;

    @Mock
    private SecurityContext securityContextMock;

    @InjectMocks
    @Autowired
    private UserController userController;

    private User u;
    private UserRole userRole;

    @Before
    public void setup() {

        u = new User();
        u.setUsername("Łukasz");
        u.setEmail("lukskar@gmail.com");
        u.setName("Łukasz");
        u.setSurname("Skarżyński");
        u.setAddressList(null);
        u.setId(1L);
        u.setMessages(null);
        u.setOfferList(null);
        u.setPassword("kolak12");

        userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRole.setId(1L);
        userRole.setUser(u);
        u.setRoles(new ArrayList<UserRole>(Arrays.asList(userRole)));

        Authentication auth = new UsernamePasswordAuthenticationToken(u, null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers(viewResolver)
                .build();
    }

    /**
     * @author Konio
     */
    @Test
    public void sHomeTest() throws Exception {

        //ToDo - rozbudowac test o usera z lista ksiazek
        //ToDo - przeniesc userow testowych do test-cfg
        //ToDo - JSON size -.- / sprawdzac czy tylko 1!

        when(userServiceMock.findUserByEmailWithLists(anyString()))
                .thenReturn(u);

        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", u))
                .andExpect(view().name("home"))
                .andExpect(forwardedUrl("/WEB-INF/view/home.jsp"));

//        this.mockMvc.perform(get("/")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                //.andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$.name", is("Łukasz")))
//                .andExpect(jsonPath("$.surname", is("Skarżyński")))
//                .andExpect(jsonPath("$.email", is("lukskar@gmail.com")))
//                .andExpect(jsonPath("$.username", is("Łukasz")))
//                .andExpect(jsonPath("$.bookswant", nullValue()))
//                .andExpect(jsonPath("$.bookshave", nullValue()));
    }

    //test: odpowiedz na /user/{id}

    //test: odpowiedz na /user/list

    //test: odpowiedz na /user/settings

    //test: odpowiedz na /user/settings/edit GET

    //test: odpowiedz na /user/settings/edit POST

    //test: odpowiedz na /user/address

    //test: odpowiedz na /user/address/edit GET

    //test: odpowiedz na /user/address/edit POST
}
