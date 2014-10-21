package me.ksiazka.dao;
import me.ksiazka.model.Address;
import me.ksiazka.model.Offer;
import me.ksiazka.model.User;

import java.util.List;

public interface UserDAO {

    //Zwraca uzytkownika po Id.
    public User getUser(long userId);

    //Zapisuje uzytkownika i zwraca jego Id.
    public long saveUser(User user);

    //Usuwa uzytkownika o podanym Id.
    public void deleteUser(long userId);

    //Update'uje uzytkownika o podanym Id na
    //wartosci przekazane w obiekcie bedacym
    //drugim parametrem.
    public void updateUser(long userId, User updatedUser);

    //Pobiera wszystkie oferty o danym statusie nalezace
    //do danego uzytkownika
    public List<Offer> getUserOffers(long userId, String offerStatus);

    //Pobiera wszystkie adresy danego uzytkonika
    public List<Address> getUserAddresses(long userId);
}
