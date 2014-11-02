package me.ksiazka.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;

@Controller("staticPageController")
public class StaticPagesControllerImpl implements StaticPagesController {

    @Override
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String showAbout() {
        return "about";
    }

    @Override
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String showContact() {
        return "contact";
    }

    @Override
    @RequestMapping(value = "/accessDenied")
    public String accessDeniec() {
        return "403";
    }
}
