package com.epam.library.service.impl;

import com.epam.library.dao.DAOException;
import com.epam.library.dao.DAOFactory;
import com.epam.library.dao.LoanCardDtoDao;
import com.epam.library.entity.Library;
import com.epam.library.entity.LoanCardStatus;
import com.epam.library.entity.dto.LoanCardDto;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class LoanCardDtoServiceImpl implements LoanCardDtoService {

    private static final Logger logger = LoggerFactory.getLogger(LoanCardDtoServiceImpl.class);

    @Override
    public List<LoanCardDto> showCardsByStatus(String status) throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DAOFactory.getInstance().getLoanCardDtoDao();
            if (status != null) {
                if (status.equalsIgnoreCase(LoanCardStatus.OPEN.name())
                        || status.equalsIgnoreCase(LoanCardStatus.CLOSED.name())) {
                    return loanCardDtoDao.getCardsByStatusCard(LoanCardStatus.valueOf(status.toUpperCase()));
                } else {
                    throw new ServiceException("Invalid status values");
                }
            }else {
                throw new ServiceException("The status value is empty.");
            }
        } catch (DAOException e) {
            logger.error("Error in services when receiving loan cards by status.");
            throw new ServiceException("Error in services when receiving loan cards by status.", e);
        }
    }

    @Override
    public List<LoanCardDto> showCardsByUser(String userId) throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DAOFactory.getInstance().getLoanCardDtoDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (userId != null) {
                if (validator.isNumber(userId)) {
                    return loanCardDtoDao.getCardsByIdUser(Long.parseLong(userId));
                } else {
                    throw new ServiceException("Invalid user ID value.");
                }
            }else {
                throw new ServiceException("The user ID value is empty.");
            }
        } catch (DAOException e) {
            logger.error("Error in services when receiving loan cards by user ID.");
            throw new ServiceException("Error in services when receiving loan cards by user ID.", e);
        }
    }

    @Override
    public List<LoanCardDto> showCardsByBook(String bookId) throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DAOFactory.getInstance().getLoanCardDtoDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (bookId != null) {
                if (validator.isNumber(bookId)) {
                    return loanCardDtoDao.getCardsByIdBook(Long.parseLong(bookId));
                } else {
                    throw new ServiceException("Invalid book ID value.");
                }
            }else {
                throw new ServiceException("The book ID value is empty.");
            }
        } catch (DAOException e) {
            logger.error("Error in services when receiving loan cards by book ID.");
            throw new ServiceException("Error in services when receiving loan cards by book ID.", e);
        }
    }

    @Override
    public List<LoanCardDto> showCardsByCityAndStatus(String city, String status) throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DAOFactory.getInstance().getLoanCardDtoDao();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            Optional<Library> optionalLibrary = libraryService.showByCity(city);
            if (optionalLibrary.isPresent()) {
                if (status != null) {
                    if (status.equalsIgnoreCase(LoanCardStatus.OPEN.name())
                            || status.equalsIgnoreCase(LoanCardStatus.CLOSED.name())) {
                        return loanCardDtoDao.getCardsByCityAndStatus(optionalLibrary.get().getCity(),
                                LoanCardStatus.valueOf(status.toUpperCase()));
                    } else {
                        throw new ServiceException("Invalid status values");
                    }
                } else {
                    throw new ServiceException("The value is empty.");
                }
            } else {
                throw new ServiceException("The city does not exist.");
            }
        } catch (DAOException e) {
            logger.error("Error in services when receiving loan cards by city and status.");
            throw new ServiceException("Error in services when receiving loan cards by city and status.", e);
        }
    }

    @Override
    public List<LoanCardDto> showCardsByCity(String city) throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DAOFactory.getInstance().getLoanCardDtoDao();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            Optional<Library> optionalLibrary = libraryService.showByCity(city);
            if (optionalLibrary.isPresent()) {
                return loanCardDtoDao.getCardsByCity(optionalLibrary.get().getCity());
            } else {
                throw new ServiceException("The city does not exist.");
            }
        } catch (DAOException e) {
            logger.error("Error in services when receiving loan cards by city.");
            throw new ServiceException("Error in services when receiving loan cards by city.", e);
        }
    }

    @Override
    public List<LoanCardDto> showAll() throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DAOFactory.getInstance().getLoanCardDtoDao();
            return loanCardDtoDao.getAll();
        } catch (DAOException e) {
            logger.error("Services error while getting all loan cards.");
            throw new ServiceException("Services error while getting all loan cards.", e);
        }
    }

    @Override
    public Optional<LoanCardDto> showCardsById(String loanCardId) throws ServiceException {
        try {
            LoanCardDtoDao loanCardDtoDao = DAOFactory.getInstance().getLoanCardDtoDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (loanCardId != null) {
                if (validator.isNumber(loanCardId)) {
                    return loanCardDtoDao.getCardsById(Long.parseLong(loanCardId.trim()));
                } else {
                    throw new ServiceException("Invalid loan card ID value");
                }
            } else {
                throw new ServiceException("The loan card ID value is empty.");
            }
        } catch (DAOException e) {
            logger.error("Services error while getting all loan cards.");
            throw new ServiceException("Services error while getting all loan cards.", e);
        }
    }
}
