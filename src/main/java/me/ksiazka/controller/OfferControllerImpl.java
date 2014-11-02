package me.ksiazka.controller;


import me.ksiazka.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;

public class OfferControllerImpl implements OfferController {

    @Autowired
    private OfferService offerService;
}
