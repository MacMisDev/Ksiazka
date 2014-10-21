package me.ksiazka.dao;

import me.ksiazka.model.Address;

import java.util.List;

public interface AddressDAO {

    //Zwraca adres po Id.
    public Address getAddres(long addressId);

    //Zapisuje adres i zwraca jego Id.
    public long saveAddress(Address address);

    //Usuwa adres o podanym Id.
    public void deleteAddress(long addressId);

    //Update'uje adres o podanym Id na
    //wartosci przekazane w obiekcie bedacym
    //drugim parametrem.
    public void updateAddress(long addressId, Address updatedAddress);
}
