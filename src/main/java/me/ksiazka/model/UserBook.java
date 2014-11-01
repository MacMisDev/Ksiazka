package me.ksiazka.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="UserBook")
public class UserBook{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "ubId")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Book book;
    @Enumerated(EnumType.STRING)
    private Condition bookCondition;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="offers",
            joinColumns = @JoinColumn(name = "ubId"),
            inverseJoinColumns = @JoinColumn(name = "offerId")
    )
    private List<Offer> bookOffers = new ArrayList<Offer>(0);

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Condition getBookCondition() {
        return bookCondition;
    }

    public void setBookCondition(Condition bookCondition) {
        this.bookCondition = bookCondition;
    }

    public List<Offer> getBookOffers() {
        return bookOffers;
    }

    public void setBookOffers(List<Offer> bookOffers) {
        this.bookOffers = bookOffers;
    }
}
