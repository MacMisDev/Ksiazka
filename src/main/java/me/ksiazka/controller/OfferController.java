package me.ksiazka.controller;

import me.ksiazka.model.Offer;
import org.springframework.ui.Model;

public interface OfferController {
    public String showOfferHome();
    public Offer showOffer(Long id);
    public String newOffer(Model model);
    public Offer newOffer(Offer offer);
}
