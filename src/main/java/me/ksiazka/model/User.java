package me.ksiazka.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Long id;
    private String name;
    private String surname;
    private String email;
    @ManyToMany
    @JoinTable(name = "booksWant",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "bookId")
    )
    private List<Book> booksWant = new ArrayList<Book>(0);
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserBook> booksHave = new ArrayList<UserBook>(0);
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<Address>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooksWant() {
        return booksWant;
    }

    public void setBooksWant(List<Book> booksWant) {
        this.booksWant = booksWant;
    }

    public List<UserBook> getBooksHave() {
        return booksHave;
    }

    public void setBooksHave(List<UserBook> booksHave) {
        this.booksHave = booksHave;
    }

}
