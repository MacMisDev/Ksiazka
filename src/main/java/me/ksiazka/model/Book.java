package me.ksiazka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Book")
@Indexed
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookId")
    @JsonIgnore
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
    @JsonIgnore
    private BookStatus bookStatus;
    @ManyToMany
    @JoinTable(name = "booksWant",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )

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

    @Length(min = 2, max = 50, message = "Tytuł musi mieć conajmniej 2 znaki, maksymalnie 50")
    @Pattern(regexp = "^[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+$", message = "Podaj poprawny tytuł")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Pattern(regexp = "^[0-9-]+$", message = "Podaj poprawny ISBN")
    public String getISBN() { return ISBN; }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Length(min = 2, max = 25, message = "Autor musi mieć conajmniej 2 znaki, maksymalnie 25")
    @Pattern(regexp = "^[.a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+$", message = "Podaj poprawnego autora")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Length(min = 2, max = 25, message = "Autor musi mieć conajmniej 2 znaki, maksymalnie 25")
    @Pattern(regexp = "^[.a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+$", message = "Podaj poprawnego wydawcę")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Length(min = 100, max = 5000, message = "Opis musi mieć minimum 100 znaków")
    @Pattern(regexp = "^[.a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ-]+$", message = "Podaj poprawnego autora")
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

    @Pattern(regexp = "^[0-9]+$", message = "Podaj same liczby")
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

    @JsonIgnore
    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    @JsonIgnore
    public List<UserBook> getBookList() {
        return bookList;
    }

    public void setBookList(List<UserBook> bookList) {
        this.bookList = bookList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (!id.equals(book.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
