package com.epam.library.service.impl;

import com.epam.library.entity.BookTypeUse;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.entity.LoanCardStatus;
import com.epam.library.service.LoanCardService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}
