package me.ksiazka.model;

/*
Opis systemu statusow oferty:
Przy pierwszym wyslaniu oferty badz jej odeslaniu po zmianach
oferta ma status "pending" poniewaz czeka na akceptacje
ze strony drugiego uzytkownika. Gdy uzytkownik, ktoremu
wyslana zostala oferta zaakceptuje ja, wtedy oferta dostaje
status accepted, ale jest to wciaz akceptacja jednostronna,
dopiero kiedy drugi uzytkownik rowniez potwierdzi zgode,
oferta staje sie confirmed, tym samym jest zrealizowana i nie
zalega juz na liscie aktualnych ofert uzytkownika.
*/
public enum OfferStatus {
    CONFIRMED, ACCEPTED, PENDING;
}
