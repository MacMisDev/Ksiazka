package me.ksiazka.model;

import javax.persistence.*;

@Entity
@Table(name="UserBook")
public class UserBook{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User userBook;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
    private String bookCondition;

    public String getBookCondition() {
        return bookCondition;
    }

    public void setBookCondition(String bookCondition) {
        this.bookCondition = bookCondition;
    }

}
