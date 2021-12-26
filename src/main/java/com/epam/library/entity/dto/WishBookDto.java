package com.epam.library.entity.dto;

import com.epam.library.entity.Author;
import com.epam.library.entity.Genre;

import java.time.LocalDate;
import java.util.List;

public class WishBookDto {

    private long wishBooksId;
    private long bookId;
    private long userId;
    private String title;
    private String publisher;
    private String description;
    private String year;
    private LocalDate added;
    private String isbn;
    private String shelf;

    public long getWishBooksId() {
        return wishBooksId;
    }

    public void setWishBooksId(long wishBooksId) {
        this.wishBooksId = wishBooksId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        WishBookDto that = (WishBookDto) obj;

        if (wishBooksId != that.wishBooksId) {
            return false;
        }
        if (bookId != that.bookId) {
            return false;
        }
        if (userId != that.userId) {
            return false;
        }
        if (!title.equals(that.title)) {
            return false;
        }
        if (!publisher.equals(that.publisher)) {
            return false;
        }
        if (!description.equals(that.description)) {
            return false;
        }
        if (!year.equals(that.year)) {
            return false;
        }
        if (!added.equals(that.added)) {
            return false;
        }
        if (!isbn.equals(that.isbn)) {
            return false;
        }
        return shelf.equals(that.shelf);
    }

    @Override
    public int hashCode() {
        int result = (int) (wishBooksId ^ (wishBooksId >>> 32));
        result = 31 * result + (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (added != null ? added.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (shelf != null ? shelf.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        line.append("WishBookDto{").append("wishBookId='").append(wishBooksId)
                .append("', bookId='").append(bookId)
                .append("', userId='").append(userId)
                .append("', title='").append(title)
                .append("', isbn='").append(isbn)
                .append("', publisher='").append(publisher)
                .append("', year='").append(year)
                .append("', shelf='").append(shelf)
                .append("', description=").append(description)
                .append("', added='").append(added).append('}');
        return line.toString();
    }
}
