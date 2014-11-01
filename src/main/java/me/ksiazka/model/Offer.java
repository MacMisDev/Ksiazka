package me.ksiazka.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Offer {

    @Id
    @GeneratedValue
    @Column(name="offerId")
    private Long id;
    @ManyToMany(mappedBy = "bookOffers")
    private List<UserBook> userBookList = new ArrayList<UserBook>(0);
    @Enumerated(value = EnumType.STRING)
    private OfferStatus offerStatus;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserBook> getUserBookList() {
        return userBookList;
    }

    public void setUserBookList(List<UserBook> userBookList) {
        this.userBookList = userBookList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }
}
