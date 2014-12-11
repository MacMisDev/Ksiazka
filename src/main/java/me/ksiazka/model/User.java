package me.ksiazka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Indexed
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Long id;
    @Column(nullable = false)
    private String name;
    private String surname;
    @Column(nullable = false, unique = true)
    @Field(index = Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    @Field(index = Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String username;
    @ManyToMany
    @JoinTable(name = "booksWant",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "bookId")
    )
    private List<Book> booksWant = new ArrayList<Book>(0);
    @OneToMany(mappedBy = "user")
    private List<UserBook> booksHave = new ArrayList<UserBook>(0);
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<Address>(0);
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<UserRole> roles = new ArrayList<UserRole>(0);
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<Message>(0);
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<OfferRelation> offerList = new ArrayList<OfferRelation>(0);

    public  User() {

    }

    public User(String name, String username, String password, String email, String surname) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.surname = surname;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    @Valid
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @Length(min = 3, max = 15, message = "Imię musi mieć conajmniej 3 znaki, maksymalnie 15")
    @Pattern(regexp = "^[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+$", message = "Podaj poprawne imię")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Email(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$", message = "Podaj poprawny email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooksWant() {
        return booksWant;
    }

    private void setBooksWant(List<Book> booksWant) {
        this.booksWant = booksWant;
    }

    public List<UserBook> getBooksHave() {
        return booksHave;
    }

    private void setBooksHave(List<UserBook> booksHave) {
        this.booksHave = booksHave;
    }

    @JsonIgnore
    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Length(min = 3, max = 20, message = "Nazwa użytkownika musi mieć conajmniej 3 znaki, maksymalnie 20")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Podaj poprawną nazwę użytkownika (tylko liczby i/lub litery, bez polskich znaków)")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @JsonIgnore
    public List<OfferRelation> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<OfferRelation> offerList) {
        this.offerList = offerList;
    }

    public void addToWantList(Book book) throws BookAlreadyExistingOnThisListException {

        for(Book b : this.booksWant) {
            if(book.getId().equals(b.getId()))
                throw new BookAlreadyExistingOnThisListException("This book already exist on this list.");
        }

        this.booksWant.add(book);
    }
    @JsonIgnore
    public int getSizeOfBooksWant() {
        return this.booksWant.size();
    }

    public Book getBookFromBooksWant(int index) {

        return this.booksWant.get(index);
    }

    public void removeFromWantList(Book book) {

        this.booksWant.remove(book);
    }

    public void removeFromWantList(int index) {

        this.booksWant.remove(index);
    }

    public void addToHaveList(UserBook userBook) throws BookAlreadyExistingOnThisListException {

        for(UserBook b : this.booksHave) {
            if(userBook.getBook().getId().equals(b.getBook().getId()))
                throw new BookAlreadyExistingOnThisListException("This book already exist on this list.");
        }

        this.booksHave.add(userBook);
    }
    @JsonIgnore
    public int getSizeOfBooksHave() {

        return this.booksHave.size();
    }

    public UserBook getBookFromBooksHave(int index) {

        return this.booksHave.get(index);
    }

    public void removeFromHaveList(int index) {

        this.booksHave.remove(index);
    }

    public void removeFromHaveList(UserBook userBook) {

        this.booksHave.remove(userBook);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;

        return true;
    }

/*    @Override
    public int hashCode() {
        return id.hashCode();
    }*/
}
