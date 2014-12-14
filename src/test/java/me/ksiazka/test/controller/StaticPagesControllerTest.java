package me.ksiazka.test.controller;

import me.ksiazka.controller.StaticPagesControllerImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class StaticPagesControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {

        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new StaticPagesControllerImpl())
                .setViewResolvers(viewResolver)
                .build();
    }

    /**
    @author Konio
     */
    @Test
    public void showAboutTest() throws Exception {

        this.mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"))
                .andExpect(forwardedUrl("/WEB-INF/view/about.jsp"));
    }

    /**
     @author Konio
     */
    @Test
    public void showContactTest() throws Exception {

        this.mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"))
                .andExpect(forwardedUrl("/WEB-INF/view/contact.jsp"));
    }

    /**
     @author Konio
     */
    @Test
    public void accessDeniedTest() throws Exception {

        this.mockMvc.perform(get("/accessDenied"))
                .andExpect(status().isOk())
                .andExpect(view().name("403"))
                .andExpect(forwardedUrl("/WEB-INF/view/403.jsp"));
    }
}
