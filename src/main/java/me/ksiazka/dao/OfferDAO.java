package me.ksiazka.dao;

import me.ksiazka.model.Offer;

public interface OfferDAO {

    //Zwraca oferte po Id.
    public Offer getOffer(long offerId);

    //Zapisuje oferte i zwraca jej Id.
    public long saveOffer(Offer offer);

    //Usuwa oferte o podanym Id.
    public void deleteOffer(long offerId);

    //Update'uje oferte o podanym Id na
    //wartosci przekazane w obiekcie bedacym
    //drugim parametrem.
    public void updateOffer(long offerId, Offer updatedOffer);
}
