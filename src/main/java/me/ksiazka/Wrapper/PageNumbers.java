package me.ksiazka.Wrapper;

public final class PageNumbers {
    private int lastBooksAddedPage;
    private int mostPopularBooksPage;

    public PageNumbers() {}

    public int getLastBooksAddedPage() {
        return lastBooksAddedPage;
    }

    public void setLastBooksAddedPage(int lastBooksAddedPage) {
        this.lastBooksAddedPage = lastBooksAddedPage;
    }

    public int getMostPopularBooksPage() {
        return mostPopularBooksPage;
    }

    public void setMostPopularBooksPage(int mostPopularBooksPage) {
        this.mostPopularBooksPage = mostPopularBooksPage;
    }
}
