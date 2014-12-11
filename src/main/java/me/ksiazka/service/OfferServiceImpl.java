package me.ksiazka.service;

import me.ksiazka.dao.OfferDAO;
import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    OfferDAO offerDAO;
    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public long save(Offer toSave) {
        return offerDAO.save(toSave);
    }

    @Override
    @Transactional
    public Offer get(long id) {
        return offerDAO.get(id);
    }

    @Override
    @Transactional
    public List<Offer> getAll() {
        return offerDAO.getAll();
    }

    @Override
    @Transactional
    public void update(Offer toUpdate) { offerDAO.update(toUpdate); }

    @Override
    @Transactional
    public void delete(Offer toDelete) { offerDAO.delete(toDelete); }

    @Override
    public Offer prepareNewOffer(User offering, User offered,
                                 List<UserBook> offeredBooks, List<UserBook> wantedBooks) {
//
        Offer offer = new Offer();
        offer.setOfferStatus(OfferStatus.PENDING);
        offer.setDate(new Date());
        offer.setOfferedBooks(offeredBooks);
        offer.setWantedBooks(wantedBooks);

        //Tworzymy relacje oferujace i uzupelniamy listy userow i oferty
        //Oferujacy
        OfferRelation offerRelationOffering = new OfferRelation();
        offerRelationOffering.setOfferRelationStatus(OfferRelationStatus.OFFERING);
        offerRelationOffering.setOffer(offer);
        offerRelationOffering.setUser(offering);
        offering.getOfferList().add(offerRelationOffering);

        //Zaoferowany
        OfferRelation offerRelationOffered = new OfferRelation();
        offerRelationOffered.setOfferRelationStatus(OfferRelationStatus.OFFERED);
        offerRelationOffered.setOffer(offer);
        offerRelationOffered.setUser(offered);
        offered.getOfferList().add(offerRelationOffered);

        offer.getOfferList().add(offerRelationOffering);
        offer.getOfferList().add(offerRelationOffered);

        userDAO.update(offering);
        userDAO.update(offered);
        offerDAO.offerRelationSave(offerRelationOffering);
        offerDAO.offerRelationSave(offerRelationOffered);

        return offer;

    }

    @Override
    public User getOfferingUser(Offer offer) {

        User offeringUser = offerDAO.getOfferingUser(offer.getId());
        return offeringUser;
    }

    @Override
    public User getOfferedUser(Offer offer) {

        User offeredUser = offerDAO.getOfferedUser(offer.getId());
        return offeredUser;

    }
}
