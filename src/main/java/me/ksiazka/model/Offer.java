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
    @OneToMany(mappedBy = "offer")
    private List<OfferRelation> offerList = new ArrayList<OfferRelation>(0);

    @ManyToMany
    @JoinTable(name="offeredBooks",
            joinColumns = @JoinColumn(name = "offerId"),
            inverseJoinColumns = @JoinColumn(name = "userBookId")
    )
    private List<UserBook> offeredBooks = new ArrayList<UserBook>(0);
    @ManyToMany
    @JoinTable(name="wantedBooks",
            joinColumns = @JoinColumn(name = "offerId"),
            inverseJoinColumns = @JoinColumn(name = "userBookId")
    )
    private List<UserBook> wantedBooks = new ArrayList<UserBook>(0);

    @Enumerated(value = EnumType.STRING)
    private OfferStatus offerStatus;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<OfferRelation> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<OfferRelation> offerList) {
        this.offerList = offerList;
    }

    public List<UserBook> getOfferedBooks() {
        return offeredBooks;
    }

    public void setOfferedBooks(List<UserBook> offeredBooks) {
        this.offeredBooks = offeredBooks;
    }

    public List<UserBook> getWantedBooks() {
        return wantedBooks;
    }

    public void setWantedBooks(List<UserBook> wantedBooks) {
        this.wantedBooks = wantedBooks;
    }

}
