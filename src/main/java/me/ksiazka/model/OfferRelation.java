package me.ksiazka.model;

import javax.persistence.*;

@Entity
public class OfferRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long offerRelationId;
    @Enumerated(value = EnumType.STRING)
    private OfferRelationStatus offerRelationStatus;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "offerId")
    private Offer offer;

    public OfferRelationStatus getOfferRelationStatus() {
        return offerRelationStatus;
    }

    public void setOfferRelationStatus(OfferRelationStatus offerRelationStatus) {
        this.offerRelationStatus = offerRelationStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Long getOfferRelationId() {
        return offerRelationId;
    }

    public void setOfferRelationId(Long offerRelationId) {
        this.offerRelationId = offerRelationId;
    }

}
