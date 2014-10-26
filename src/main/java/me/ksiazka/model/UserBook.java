package me.ksiazka.model;

import javax.persistence.*;

@Entity
@Table(name="UserBook")
public class UserBook{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Book book;
    @Enumerated(EnumType.STRING)
    private Condition bookCondition;

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
}
