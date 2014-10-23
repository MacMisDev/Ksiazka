package me.ksiazka.model;

import javax.persistence.*;

@Entity
@Table(name="UserBook")
public class UserBook extends Book {

    private String bookCondition;
    @ManyToOne
    private User user;

    public String getBookCondition() {
        return bookCondition;
    }

    public void setBookCondition(String bookCondition) {
        this.bookCondition = bookCondition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
