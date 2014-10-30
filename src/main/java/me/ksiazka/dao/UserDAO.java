package me.ksiazka.dao;
import me.ksiazka.model.*;

import java.util.List;

public interface UserDAO {

    //Zwraca uzytkownika po Id.
    public User getUser(long userId);

    //Pobranie wszystkich uzytkownikow z bazy.
    public List<User> getAll();

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

    //Dodanie ksiazki na liste posiadanych ksiazek uzytkownika
    //Zwraca id dodanego UserBook
    public long addToHaveList(User user, Book book, Condition bookCondition);

    //Usuniecie ksiazki z listy posiadanych ksiazek uzytkownika
    public void deleteFromHaveList(long userId, long userBookId);

    //Aktualizacja ksiazki danego uzytkownika na jego liscie have
    public void updateBookFromHaveList(long userBookId, UserBook updatedUserBook);

    //Pobiera wszystkie ksiazki uzytkownika z listy have
    public List<UserBook> getUserHaveList(long userId);

    //Pobiera UserBook po id
    public UserBook getUserBook(long userBookId);

    //Dodaje ksiazke do listy want uzytkownika
    public long addToWantList(long userId, long bookId);

    //Usuwa ksiazka z listy want uzytkownika
    public void deleteFromWantList(long userId, long bookId);

    //Zwraca uzytkownika po jego emailu.
    public User getUserByEmail(String email);
}
