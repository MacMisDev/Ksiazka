package me.ksiazka.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;

@Controller("homeController")
public class HomeControllerImpl implements HomeController{

    @RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
    public String showHomePage() {
        return "home";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String showAbout() {
        return "about";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String showContact() {
        return "contact";
    }
}
