package me.ksiazka.controller;


import me.ksiazka.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("offerController")
@RequestMapping(value = "offer")
public class OfferControllerImpl implements OfferController {

    @Autowired
    private OfferService offerService;
}
