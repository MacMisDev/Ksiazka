package me.ksiazka.controller;


import me.ksiazka.model.Offer;
import me.ksiazka.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("offerController")
@RequestMapping("/offer")
public class OfferControllerImpl implements OfferController {

    @Autowired
    private OfferService offerService;

    @Override
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String showOfferHome() {
        return "redirect:/offer/list";
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody Offer showOffer(@PathVariable Long id) {
        return offerService.get(id);
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newOffer(Model model) {
        model.addAttribute("offer", new Offer());
        return "offer/newOffer";
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public @ResponseBody Offer newOffer(@RequestBody Offer offer) {
        return offerService.get(offerService.save(offer));
    }

    //todo
    @Override
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String offerList(Model model) {
        return "offer/offerList";
    }
}
