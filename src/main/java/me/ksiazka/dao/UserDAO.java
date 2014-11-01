package me.ksiazka.dao;
import me.ksiazka.model.*;

import java.util.List;

public interface UserDAO extends BasicDAOInterface<User> {

    //Jesli uzytkownik istnieje to go zwraca, jesl nie to zwraca null
    public User findUserByUsername(String username);

    //Jesli uzytkownik o podanym emailu istnieje to go zwraca, jesl nie to zwraca null
    public User findUserByEmail(String email);
}