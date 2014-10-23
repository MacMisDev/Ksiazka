package me.ksiazka.dao;
import me.ksiazka.model.Book;
import org.hibernate.SessionFactory;

public interface BookDAO {

    //Zwraca ksiazke po Id.
    public Book getBook(long bookId);

    //Zapisuje ksiazke i zwraca jej Id.
    public long saveBook(Book book);

    //Usuwa ksiazke o podanym Id.
    public void deleteBook(long bookId);

    //Update'uje ksiazke o podanym Id na
    //wartosci przekazane w obiekcie bedacym
    //drugim parametrem.
    public void updateBook(long bookId, Book updatedBook);

    public long testowySaveUserBook(Book book);

}
