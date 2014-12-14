package me.ksiazka.Wrapper;

import me.ksiazka.model.Book;

import java.util.List;

public final class BookLists {
    private List<Book> lastBooksAdded;
    private List<Book> mostPopularBooks;
    private int maxPages;

    public BookLists(){}

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    public List<Book> getLastBooksAdded() {
        return lastBooksAdded;
    }

    public void setLastBooksAdded(List<Book> lastBooksAdded) {
        this.lastBooksAdded = lastBooksAdded;
    }

    public List<Book> getMostPopularBooks() {
        return mostPopularBooks;
    }

    public void setMostPopularBooks(List<Book> mostPopularBooks) {
        this.mostPopularBooks = mostPopularBooks;
    }
}
