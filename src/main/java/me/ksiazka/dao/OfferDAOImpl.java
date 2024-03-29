package me.ksiazka.dao;

import me.ksiazka.model.Offer;
import me.ksiazka.model.OfferRelation;
import me.ksiazka.model.User;
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

        return (Long) this.sessionFactory.getCurrentSession().save(toSave);

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

        this.sessionFactory.getCurrentSession().update(toUpdate);

    }

    @Override
    public void delete(Offer toDelete) {

        this.sessionFactory.getCurrentSession().delete(toDelete);

    }

    @Override
    public void delete(long id) {

        Offer offerToDelete = (Offer) this.sessionFactory.getCurrentSession().get(Offer.class, id);
        this.sessionFactory.getCurrentSession().delete(offerToDelete);

    }

    @Override
    public Long offerRelationSave(OfferRelation or){
        return (Long) this.sessionFactory.getCurrentSession().save(or);
    }

    @Override
    public User getOfferingUser(Long offerId){

        String query = "Select u from User u LEFT JOIN u.offerList o where o.offer.id = :id and offerRelationStatus='Offering'";
        Query q = this.sessionFactory.getCurrentSession().createQuery(query).setParameter("id", offerId);
        if(q.list().isEmpty())
            return null;
        else
            return (User) q.list().get(0);

    }

    @Override
    public User getOfferedUser(Long offerId){

        String query = "Select u from User u LEFT JOIN u.offerList o where o.offer.id = :id and offerRelationStatus='Offered'";
        Query q = this.sessionFactory.getCurrentSession().createQuery(query).setParameter("id", offerId);
        if(q.list().isEmpty())
            return null;
        else
            return (User) q.list().get(0);

    }
}
