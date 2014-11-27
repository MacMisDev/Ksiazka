package me.ksiazka.misc;


import me.ksiazka.model.Address;
import me.ksiazka.model.User;

public class UserDetails {
    private Address address;
    private User user;

    public UserDetails() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
