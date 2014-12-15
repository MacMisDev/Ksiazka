package me.ksiazka.dao;
import me.ksiazka.model.*;

import java.util.List;

public interface UserDAO extends BasicDAOInterface<User> {

    //Jesli uzytkownik istnieje to go zwraca, jesl nie to zwraca null
    public User findUserByUsername(String username);

    //Jesli uzytkownik o podanym emailu istnieje to go zwraca, jesl nie to zwraca null
    public User findUserByEmail(String email);

    //Wyszukiwanie po emailu
    public List<User> searchByEmail(String email) throws InterruptedException;

    ////Query update'ujaca przed usunieciem User
    public void updateOfferRelationBeforeDelete(User user);

    public void updateUserBookBeforeDelete(User user);

    public List<User> getUsersForBookHardDelete(Book toDelete);

    //Jesli uzytkownik o podanym emailu istnieje to go zwraca oraz inicjalizuje listy booksWant i booksHave, jesli nie to zwraca null
    public User findUserByEmailWithLists(String email);

    //Jesli uzytkownik o podanym emailu istnieje to go zwraca oraz inicjalizuje liste adresow, jesli nie to zwraca null
    public User findUserByEmailWithAddress(String email);

    public User getUserWithLists(long id);

    public List<User> getUsersForOfferDelete(Long offerId);
}