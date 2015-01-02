package me.ksiazka.dao;

import me.ksiazka.model.Offer;
import me.ksiazka.model.OfferRelation;
import me.ksiazka.model.User;

import java.util.List;

public interface OfferDAO extends BasicDAOInterface<Offer> {

    /*
        OfferRelation jest ściścle uzależnione od oferty,
        dlatego proponuje, aby metody dla tej relacji umieścić w OfferDao
     */
    public Long offerRelationSave(OfferRelation or);
    public User getOfferingUser(Long offerId);
    public User getOfferedUser(Long offerId);

}
