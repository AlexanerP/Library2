package com.epam.library.entity;


import java.io.Serializable;
import java.time.LocalDate;

public class LoanCard implements Serializable {

    private static final long serialVersionUID = -6359287843904176135L;

    private long loanCardId;
    private long bookId;
    private long userId;
    private String cityLibrary;
    private LocalDate takingBook;
    private String returnBook;
    private LocalDate deadline;
    private LoanCardStatus status;
    private BookTypeUse typeUse;

    public long getLoanCardId() {
        return loanCardId;
    }

    public void setLoanCardId(long loanCardId) {
        this.loanCardId = loanCardId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCityLibrary() {
        return cityLibrary;
    }

    public void setCityLibrary(String cityLibrary) {
        this.cityLibrary = cityLibrary;
    }

    public LocalDate getTakingBook() {
        return takingBook;
    }

    public void setTakingBook(LocalDate takingBook) {
        this.takingBook = takingBook;
    }

    public String getReturnBook() {
        return returnBook;
    }

    public void setReturnBook(String returnBook) {
        this.returnBook = returnBook;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LoanCardStatus getStatus() {
        return status;
    }

    public void setStatus(LoanCardStatus status) {
        this.status = status;
    }

    public BookTypeUse getTypeUse() {
        return typeUse;
    }

    public void setTypeUse(BookTypeUse typeUse) {
        this.typeUse = typeUse;
    }
}
