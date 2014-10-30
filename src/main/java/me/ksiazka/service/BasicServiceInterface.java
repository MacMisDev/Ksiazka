package me.ksiazka.service;

import java.util.List;

public interface BasicServiceInterface<T> {

    //Zapis
    public long save(T toSave);

    //Odczyt
    public T get(long id);

    //Odczyt wszystkich
    public List<T> getAll();

    //Update
    public void update(T toUpdate);

    //Delete
    public void delete(T toDelete);
}
