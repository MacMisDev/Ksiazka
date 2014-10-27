package me.ksiazka.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookId")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String ISBN;
    private String author;
    private String publisher;
    @Column(length = 500)
    private String description;
    private int publicationYear;
    private int pages;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus bookStatus;
    @ManyToMany(mappedBy = "booksWant")
    private List<User> user = new ArrayList<User>(0);
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<UserBook> bookList = new ArrayList<UserBook>(0);

    public Book() {
        this.bookStatus = BookStatus.AWAITING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

}
