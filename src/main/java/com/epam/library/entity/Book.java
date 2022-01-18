package com.epam.library.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {

    private static final long serialVersionUID = 766391284248913856L;

    private long bookId;
    private int libraryId;
    private String isbn;
    private String title;
    private int quantity;
    private int borrow;
    private String publisher;
    private String description;
    private String year;
    private LocalDate added;
    private String shelf;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBorrow() {
        return borrow;
    }

    public void setBorrow(int borrow) {
        this.borrow = borrow;
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

        Book book = (Book) obj;

        if (bookId != book.bookId) {
            return false;
        }
        if (quantity != book.quantity) {
            return false;
        }
        if (borrow != book.borrow) {
            return false;
        }
        if (!isbn.equals(book.isbn)) {
            return false;
        }
        if (!title.equals(book.title)) {
            return false;
        }
        if (!publisher.equals(book.publisher)) {
            return false;
        }
        if (!description.equals(book.description)) {
            return false;
        }
        if (libraryId != book.libraryId) {
            return false;
        }
        if (!year.equals(book.year)) {
            return false;
        }
        if (!added.equals(book.added)) {
            return false;
        }
        return shelf.equals(book.shelf);
    }

    @Override
    public int hashCode() {
        int result = (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + libraryId;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + borrow;
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (added != null ? added.hashCode() : 0);
        result = 31 * result + (shelf != null ? shelf.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        line.append("Book{").append("bookId='").append(bookId)
                .append("', libraryId='").append(libraryId)
                .append("', title='").append(title)
                .append("', isbn='").append(isbn)
                .append("', quantity='").append(quantity)
                .append("', borrow='").append(borrow)
                .append("', publisher='").append(publisher)
                .append("', year='").append(year)
                .append("', shelf='").append(shelf)
                .append("', description='").append(description)
                .append(", added=").append(added).append('}');
        return line.toString();
    }
}
