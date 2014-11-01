package me.ksiazka.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Offer {

    @Id
    @GeneratedValue
    @Column(name="offerId")
    private Long id;
    @ManyToMany(mappedBy = "bookOffers")
    private List<UserBook> userBookList = new ArrayList<UserBook>(0);

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
}
