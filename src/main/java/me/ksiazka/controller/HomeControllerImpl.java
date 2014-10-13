package me.ksiazka.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;

@Controller("homeController")
public class HomeControllerImpl implements HomeController{

    @RequestMapping({"/", "home"})
    public String showHomePage() {
        return "home";
    }
}
