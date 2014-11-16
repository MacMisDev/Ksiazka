package me.ksiazka.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    @ManyToMany(mappedBy = "offeredBooks")
    private List<Offer> offeredBooks = new ArrayList<Offer>(0);
    @ManyToMany(mappedBy = "wantedBooks")
    private List<Offer> wantedBooks = new ArrayList<Offer>(0);

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

    public List<Offer> getOfferedBooks() {
        return offeredBooks;
    }

    public void setOfferedBooks(List<Offer> offeredBooks) {
        this.offeredBooks = offeredBooks;
    }

    public List<Offer> getWantedBooks() {
        return wantedBooks;
    }

    public void setWantedBooks(List<Offer> wantedBooks) {
        this.wantedBooks = wantedBooks;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBook)) return false;

        UserBook userBook = (UserBook) o;

        if (!id.equals(userBook.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
