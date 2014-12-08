package me.ksiazka.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String buildingNumber;
    private String flatNumber;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String postalCode;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Length(min = 2, max = 50, message = "Nazwa ulicy musi mieć minimum 2 znaki")
    @Pattern(regexp = "^[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+$", message = "Podaj poprawną nazwę")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @NotEmpty(message = "Podaj numer budynku")
    @Pattern(regexp = "^[0-9]+$", message = "Podaj poprawny numer")
    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    @NotEmpty(message = "Podaj numer mieszkania")
    @Pattern(regexp = "^[0-9]+$", message = "Podaj poprawny numer")
    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    @Length(min = 2, max = 20, message = "Nazwa miasta musi mieć minimum 2 znaki")
    @Pattern(regexp = "^[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+$", message = "Podaj poprawną nazwę miasta")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotEmpty(message = "Podaj kod pocztowy")
    @Pattern(regexp = "^\\d{2}(?:[-]\\d{3})$", message = "Podaj poprawny kod pocztowy")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
