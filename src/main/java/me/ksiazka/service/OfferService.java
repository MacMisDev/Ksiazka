package me.ksiazka.service;

import me.ksiazka.model.Offer;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;

import java.util.List;

public interface OfferService extends BasicServiceInterface<Offer> {

    //Metoda przyjmuje cztery parametry:
    //offering - uzytkownik skladajacy oferte
    //offered - uzytkownik, ktoremu zlozona zostaje oferta
    //offeredBooks - lista ksiazek, ktore zaproponowal uzytkownik 'offering'
    //wantedBooks - lista ksiazek, ktore chce przyjac uzytkownik 'offering'
    //od uzytkownika 'offered'.
    public Offer prepareNewOffer(User offering, User offered,
                                 List<UserBook> offeredBooks, List<UserBook> wantedBooks);

    public User getOfferingUser(Offer offer);
    public User getOfferedUser(Offer offer);
}
