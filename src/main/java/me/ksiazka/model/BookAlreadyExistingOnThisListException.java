package me.ksiazka.model;

public class BookAlreadyExistingOnThisListException extends Exception {

    public BookAlreadyExistingOnThisListException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BookAlreadyExistingOnThisListException(String msg) {
        super(msg);
    }
}
