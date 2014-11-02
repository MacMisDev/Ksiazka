package me.ksiazka.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Book")
@Indexed
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookId")
    private Long id;
    @Column(nullable = false)
    @Field(index = Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String title;
    @Column(nullable = false, unique = true)
    private String ISBN;
    @Column(nullable = false)
    @Field(index = Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String author;
    private String publisher;
    @Column(length = 1000)
    private String description;
    private int publicationYear;
    private int pages;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus bookStatus;
    @ManyToMany(mappedBy = "booksWant", cascade = CascadeType.ALL)
    private List<User> user = new ArrayList<User>(0);
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<UserBook> bookList = new ArrayList<UserBook>(0);

    public Book() {
        this.bookStatus = BookStatus.AWAITING;
    }

    public Book(String title, String ISBN, String author, String publisher, String description,
                int publicationYear, int pages, BookStatus bookStatus) {
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.publicationYear = publicationYear;
        this.pages = pages;
        this.bookStatus = bookStatus;
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
