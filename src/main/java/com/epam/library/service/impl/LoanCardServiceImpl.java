package com.epam.library.service.impl;

import com.epam.library.entity.BookTypeUse;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.entity.LoanCardStatus;
import com.epam.library.service.LoanCardService;
import com.epam.library.service.ServiceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanCardServiceImpl implements LoanCardService {


    @Override
    public List<LoanCardDto> showCardsByUser(long idUser) {
        System.out.println("LoanCardServiceImpl");
        List<LoanCardDto> cards = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LoanCardDto card = new LoanCardDto();
            card.setLoanCardDtoId(1l);
            card.setLastName("LAst name");
            card.setSecondName("Second name");
            card.setUserId(1l);
            card.setBookId(1l);
            card.setTitle("title");
            card.setIsbn("isbn");
            card.setTakingBook(LocalDate.now());
            card.setReturnBook(LocalDate.now());
            card.setDeadline(LocalDate.now());
            card.setTypeUse(BookTypeUse.READ_ROOM);
            card.setStatus(LoanCardStatus.OPEN);

            cards.add(card);


        }
        System.out.println("Size - " + cards.size());
        return cards;
    }

    @Override
    public List<LoanCardDto> showCardsByStatus(LoanCardStatus status) throws ServiceException {
        List<LoanCardDto> cards = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LoanCardDto card = new LoanCardDto();
            card.setLoanCardDtoId(1l);
            card.setLastName("LAst name");
            card.setSecondName("Second name");
            card.setUserId(1l);
            card.setBookId(1l);
            card.setTitle("title");
            card.setIsbn("isbn");
            card.setTakingBook(LocalDate.now());
            card.setReturnBook(LocalDate.now());
            card.setDeadline(LocalDate.now());
            card.setTypeUse(BookTypeUse.READ_ROOM);
            card.setStatus(LoanCardStatus.OPEN);

            cards.add(card);


        }
        System.out.println("Size - " + cards.size());
        return cards;
    }

    @Override
    public List<LoanCardDto> showCardsByUser(String userId) throws ServiceException {
        List<LoanCardDto> cards = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LoanCardDto card = new LoanCardDto();
            card.setLoanCardDtoId(1l);
            card.setLastName("LAst name");
            card.setSecondName("Second name");
            card.setUserId(1l);
            card.setBookId(1l);
            card.setTitle("title");
            card.setIsbn("isbn");
            card.setTakingBook(LocalDate.now());
            card.setReturnBook(LocalDate.now());
            card.setDeadline(LocalDate.now());
            card.setTypeUse(BookTypeUse.READ_ROOM);
            card.setStatus(LoanCardStatus.OPEN);

            cards.add(card);


        }
        System.out.println("Size - " + cards.size());
        return cards;
    }

    @Override
    public List<LoanCardDto> showCardsByBook(String bookId) throws ServiceException {
        List<LoanCardDto> cards = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LoanCardDto card = new LoanCardDto();
            card.setLoanCardDtoId(1l);
            card.setLastName("LAst name");
            card.setSecondName("Second name");
            card.setUserId(1l);
            card.setBookId(1l);
            card.setTitle("title");
            card.setIsbn("isbn");
            card.setTakingBook(LocalDate.now());
            card.setReturnBook(LocalDate.now());
            card.setDeadline(LocalDate.now());
            card.setTypeUse(BookTypeUse.READ_ROOM);
            card.setStatus(LoanCardStatus.OPEN);

            cards.add(card);


        }
        System.out.println("Size - " + cards.size());
        return cards;
    }

    @Override
    public List<LoanCardDto> showCardsByCityAndStatus(String city, LoanCardStatus status) throws ServiceException {
        List<LoanCardDto> cards = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LoanCardDto card = new LoanCardDto();
            card.setLoanCardDtoId(1l);
            card.setLastName("LAst name");
            card.setSecondName("Second name");
            card.setUserId(1l);
            card.setBookId(1l);
            card.setTitle("title");
            card.setIsbn("isbn");
            card.setTakingBook(LocalDate.now());
            card.setReturnBook(LocalDate.now());
            card.setDeadline(LocalDate.now());
            card.setTypeUse(BookTypeUse.READ_ROOM);
            card.setStatus(LoanCardStatus.OPEN);

            cards.add(card);


        }
        System.out.println("Size - " + cards.size());
        return cards;
    }

    @Override
    public Optional<LoanCardDto> showCardsById(String loanCardId) throws ServiceException {
        LoanCardDto card = new LoanCardDto();
        card.setLoanCardDtoId(1l);
        card.setLastName("LAst name");
        card.setSecondName("Second name");
        card.setUserId(1l);
        card.setBookId(1l);
        card.setTitle("title");
        card.setIsbn("isbn");
        card.setTakingBook(LocalDate.now());
        card.setReturnBook(LocalDate.now());
        card.setDeadline(LocalDate.now());
        card.setTypeUse(BookTypeUse.READ_ROOM);
        card.setStatus(LoanCardStatus.OPEN);
        return Optional.of(card);
    }

    @Override
    public List<LoanCardDto> showCardsByCity(String city) throws ServiceException {
        List<LoanCardDto> cards = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LoanCardDto card = new LoanCardDto();
            card.setLoanCardDtoId(1l);
            card.setLastName("LAst name");
            card.setSecondName("Second name");
            card.setUserId(1l);
            card.setBookId(1l);
            card.setTitle("title");
            card.setIsbn("isbn");
            card.setTakingBook(LocalDate.now());
            card.setReturnBook(LocalDate.now());
            card.setDeadline(LocalDate.now());
            card.setTypeUse(BookTypeUse.READ_ROOM);
            card.setStatus(LoanCardStatus.OPEN);

            cards.add(card);


        }
        System.out.println("Size - " + cards.size());
        return cards;
    }

    @Override
    public List<LoanCardDto> showAll() throws ServiceException {
        List<LoanCardDto> cards = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LoanCardDto card = new LoanCardDto();
            card.setLoanCardDtoId(1l);
            card.setLastName("LAst name");
            card.setSecondName("Second name");
            card.setUserId(1l);
            card.setBookId(1l);
            card.setTitle("title");
            card.setIsbn("isbn");
            card.setTakingBook(LocalDate.now());
            card.setReturnBook(LocalDate.now());
            card.setDeadline(LocalDate.now());
            card.setTypeUse(BookTypeUse.READ_ROOM);
            card.setStatus(LoanCardStatus.OPEN);

            cards.add(card);


        }
        System.out.println("Size - " + cards.size());
        return cards;
    }
}
