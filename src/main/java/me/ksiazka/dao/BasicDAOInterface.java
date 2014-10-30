package me.ksiazka.dao;

import java.lang.reflect.Type;
import java.util.List;

public interface BasicDAOInterface<T> {

    //Zapis
    public long save(T toSave);

    //Odczyt
    public T get(long id);

    //Odczyt wszystkich
    public List<T> getAll();

    //Update
    public void update(T toUpdate);

    //Usuwanie wersja po obiekcie
    public void delete(T toDelete);

    //Usuwanie wersja po id
    public void delete(long id);
}
