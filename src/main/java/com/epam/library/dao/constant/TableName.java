package com.epam.library.dao.constant;

public class TableName {

    private TableName() {}

    public static final String USER = "users";
    public static final String USER_STATUS = "user_statuses";
    public static final String ROLE = "role";
    public static final String BOOK = "books";
    public static final String ORDER = "orders";
    public static final String ORDER_STATUS = "order_statuses";
    public static final String LIBRARY = "libraries";
    public static final String LIBRARY_STATUS = "library_statuses";
    public static final String AUTHORS = "authors";
    public static final String GENRES = "genres";
    public static final String LOAN_CARDS = "loan_cards";
    public static final String LOAN_CARDS_STATUS = "card_statuses";
    public static final String WISH_BOOK = "wish_books";
    public static final String WB_H_U = "wish_book_has_users";

    public static final String A_H_B = "authors_has_book";
    public static final String G_H_B = "genres_has_book";
    public static final String LC_H_U = "loan_cards_has_users";


    public static final String L_H_LC = "library_has_loan_card";
    public static final String B_H_LC = "book_has_loan_cards";
}
