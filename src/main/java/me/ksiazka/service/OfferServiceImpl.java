package me.ksiazka.service;

import me.ksiazka.dao.OfferDAO;
import me.ksiazka.model.Offer;
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
        return 0;
    }

    @Override
    @Transactional
    public Offer get(long id) {
        return null;
    }

    @Override
    @Transactional
    public List<Offer> getAll() {
        return null;
    }

    @Override
    @Transactional
    public void update(Offer toUpdate) {

    }

    @Override
    @Transactional
    public void delete(Offer toDelete) {

    }
}
