package me.ksiazka.dao;

import me.ksiazka.model.Offer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfferDAOImpl implements OfferDAO {

    @Override
    public long save(Offer toSave) {
        return 0;
    }

    @Override
    public Offer get(long id) {
        return null;
    }

    @Override
    public List<Offer> getAll() {
        return null;
    }

    @Override
    public void update(Offer toUpdate) {

    }

    @Override
    public void delete(Offer toDelete) {

    }

    @Override
    public void delete(long id) {

    }
}
