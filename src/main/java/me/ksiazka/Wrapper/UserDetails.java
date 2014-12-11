package me.ksiazka.Wrapper;


import me.ksiazka.model.Address;
import me.ksiazka.model.User;

import javax.validation.Valid;

public class UserDetails {

    private Address address;
    private User user;

    public UserDetails() {
    }

    @Valid
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Valid
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
