package me.ksiazka.dao;
import me.ksiazka.model.Message;

public interface MessageDAO {

    //Zwraca wiadomosc po Id.
    public Message getMessage(long msgId);

    //Zapisuje wiadomosc i zwraca jej Id.
    public long saveMessage(Message msg);

    //Usuwa wiadomosc o podanym Id.
    public void deleteMessage(long msgId);
}
