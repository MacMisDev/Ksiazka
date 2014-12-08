package me.ksiazka.service;

import me.ksiazka.dao.OfferDAO;
import me.ksiazka.model.Offer;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    OfferDAO offerDAO;

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

        return null;
        //Pamietaj Krzysiu o ustawieniu daty kiedy zostala stworzona oferta
        //i statusu PENDING
    }

    @Override
    public User getOfferingUser(Offer offer) {
        //Ma zwrocic uzytkownika, ktory w danej ofercie jest 'offering' - patrz opis
        //metody prepareNewOffer w interfejsie
        return null;
    }

    @Override
    public User getOfferedUser(Offer offer) {
        //Analogicznie j.w. tylko ze zwraca 'offered'
        return null;
    }
}
