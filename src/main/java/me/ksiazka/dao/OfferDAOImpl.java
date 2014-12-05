package me.ksiazka.dao;

import me.ksiazka.model.Offer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfferDAOImpl implements OfferDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public long save(Offer toSave) {
        return 0;
    }

    @Override
    public Offer get(long id) {

        Offer offer = (Offer) this.sessionFactory.getCurrentSession().get(Offer.class, id);
        return offer;

    }

    @Override
    public List<Offer> getAll() {

        String query = "FROM Offer";
        Query listQuery = this.sessionFactory.getCurrentSession().createQuery(query);
        List<Offer> offers = (List<Offer>) listQuery.list();

        return offers;

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
