package com.epam.library.service.impl;

import com.epam.library.entity.Author;
import com.epam.library.entity.Genre;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class BookDtoServiceImpl implements BookDtoService {

    @Override
    public List<BookDto> showCatalog() throws ServiceException {
        System.out.println("Connection DB showCatalog");
        List<BookDto> bookDtos = new ArrayList<>();
        for(int i = 0; i < 10; i++){
        BookDto bookDTO = new BookDto();
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Author 1"));
//        authors.add(new Author("Author 2"));
//        authors.add(new Author("Author 3"));

            List<Genre> genres = new ArrayList<>();
            genres.add(new Genre("genre"));
            genres.add(new Genre("genre 1"));

        bookDTO.setTitle("The lord of War");
        bookDTO.setIsbn("16451grdgsgs");
        bookDTO.setPublisher("publisher");
        bookDTO.setYear("2000");
        bookDTO.setAuthors(authors);
        bookDTO.setGenres(genres);
        bookDTO.setBorrow(2);
        bookDTO.setQuantity(7);



            bookDTO.setBookDtoId(Long.valueOf(i));
            bookDtos.add(bookDTO);
        }
        return bookDtos;
    }

    @Override
    public List<BookDto> showBookByParameter(String title, String isbn, String genre) throws ServiceException {
        System.out.println("Parameter: title/" + title + ". isbn/" + isbn + ". genre/" + genre);

        System.out.println("Connection DB showCatalog. showByPage");
        List<BookDto> bookDtos = new ArrayList<>();
        for(int i =  0; i < 20; i++){
            BookDto bookDTO = new BookDto();
            List<Author> authors = new ArrayList<>();
            authors.add(new Author("Author 1"));
//        authors.add(new Author("Author 2"));
//        authors.add(new Author("Author 3"));

            List<Genre> genres = new ArrayList<>();
            genres.add(new Genre("genre"));
            genres.add(new Genre("genre 1"));

            bookDTO.setTitle("The lord of War");
            bookDTO.setIsbn("16451grdgsgs");
            bookDTO.setPublisher("publisher");
            bookDTO.setYear("2000");
            bookDTO.setAuthors(authors);
            bookDTO.setGenres(genres);
            bookDTO.setBorrow(2);
            bookDTO.setQuantity(7);



            bookDTO.setBookDtoId(Long.valueOf(i));
            bookDtos.add(bookDTO);
        }
        return bookDtos;
    }

    @Override
    public BookDto showBook(long id) {
        System.out.println("Connection DB showBook");

        /*

        if (optional.isPresent()) {
            book.setBookId(optional.map(BookDTO :: getBookId).orElse(0l));
            book.setTitle(optional.map(BookDTO :: getTitle).orElse("-"));
            book.setIsbn(optional.map(BookDTO :: getIsbn).orElse("-"));
            book.setPublisher(optional.map(BookDTO :: getPublisher).orElse("-"));
            book.setAuthors(optional.map(BookDTO :: getAuthors).orElse(new ArrayList<>(
                    (Collection<? extends Author>) new Author("No author"))));
            book.setGenres(optional.map(BookDTO :: getGenres).orElse(new ArrayList<>(
                    (Collection<? extends Genre>) new Genre("No genre"))));
            book.setYear(optional.map(BookDTO :: getYear).orElse("-"));
            book.setShelf(optional.map(BookDTO :: getShelf).orElse("-"));
            book.setQuantity(optional.map(BookDTO :: getQuantity).orElse(0));
            book.setBorrow(optional.map(BookDTO :: getBorrow).orElse(0));
            book.setCityLibrary(optional.map(BookDTO::getCityLibrary).orElse("-"));
            book.setAdded(optional.map(BookDTO::getAdded).orElse(LocalDate.now()));
            book.setDescription(optional.map(BookDTO::getDescription).orElse("-"));

        }
         */
        BookDto bookDTO = new BookDto();
        bookDTO.setBookDtoId(5L);
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Author 1"));
        authors.add(new Author("Author 2"));
        authors.add(new Author("Author 3"));
        bookDTO.setAuthors(authors);

        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre("genre"));
        genres.add(new Genre("genre 1"));

        bookDTO.setGenres(genres);


        bookDTO.setTitle("The lord of War");
        bookDTO.setIsbn("16451grdgsgs");
        bookDTO.setPublisher("publisher");
        bookDTO.setYear("2000");
        bookDTO.setBorrow(2);
        bookDTO.setQuantity(7);
        return bookDTO;
    }

    @Override
    public int showCountBook() throws ServiceException {
        return 100;
    }

    @Override
    public List<BookDto> showByPage(int page) throws ServiceException {
        System.out.println("Connection DB showCatalog. showByPage");
        List<BookDto> bookDtos = new ArrayList<>();
        for(int i = page * 20 - 20; i < page * 20; i++){
            BookDto bookDTO = new BookDto();
            List<Author> authors = new ArrayList<>();
            authors.add(new Author("Author 1"));
//        authors.add(new Author("Author 2"));
//        authors.add(new Author("Author 3"));

            List<Genre> genres = new ArrayList<>();
            genres.add(new Genre("genre"));
            genres.add(new Genre("genre 1"));

            bookDTO.setTitle("The lord of War");
            bookDTO.setIsbn("16451grdgsgs");
            bookDTO.setPublisher("publisher");
            bookDTO.setYear("2000");
            bookDTO.setAuthors(authors);
            bookDTO.setGenres(genres);
            bookDTO.setBorrow(2);
            bookDTO.setQuantity(7);



            bookDTO.setBookDtoId(Long.valueOf(i));
            bookDtos.add(bookDTO);
        }
        return bookDtos;
    }
}
