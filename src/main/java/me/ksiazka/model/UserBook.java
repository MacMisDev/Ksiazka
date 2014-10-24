package me.ksiazka.model;

import javax.persistence.*;

@Entity
@Table(name="UserBook")
public class UserBook extends Book {

    private String bookCondition;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User userBook;

    public String getBookCondition() {
        return bookCondition;
    }

    public void setBookCondition(String bookCondition) {
        this.bookCondition = bookCondition;
    }

    public User getUserBook() {
        return userBook;
    }

    public void setUserBook(User userBook) {
        this.userBook = userBook;
    }
}
