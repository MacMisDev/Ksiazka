package me.ksiazka.dao;
import me.ksiazka.model.Book;
import me.ksiazka.model.User;
import me.ksiazka.model.UserBook;
import org.hibernate.SessionFactory;

public interface BookDAO {

    //Zwraca ksiazke po Id.
    public Book getBook(long bookId);

    //Zapisuje ksiazke i zwraca jej Id.
    public Long saveBook(Book book);

    //Usuwa ksiazke o podanym Id.
    public void deleteBook(long bookId);

    //Update'uje ksiazke o podanym Id na
    //wartosci przekazane w obiekcie bedacym
    //drugim parametrem.
    public void updateBook(long bookId, Book updatedBook);

    public Long testowySaveUserBook(User user, Book book);

    public UserBook getUserBook(long bookID);
}
