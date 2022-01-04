package com.epam.library.service.impl;

import com.epam.library.dao.DAOException;
import com.epam.library.dao.DAOFactory;
import com.epam.library.dao.WishBookDtoDao;
import com.epam.library.entity.dto.WishBookDto;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.ServiceValidator;
import com.epam.library.service.WishBookDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class WishBookDtoServiceImpl implements WishBookDtoService {

    private static final Logger logger = LoggerFactory.getLogger(WishBookDtoServiceImpl.class);

    @Override
    public List<WishBookDto> showBooksUser(String userId) throws ServiceException {
        try {
            WishBookDtoDao wishBookDtoDao = DAOFactory.getInstance().getWishBookDtoDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (validator.isNumber(userId)) {
                return wishBookDtoDao.getBooks(Long.parseLong(userId.trim()));
            }else {
                throw new ServiceException("Invalid ID");
            }

        }catch (DAOException e) {
            logger.error("Error in services when retrieving selected user books.");
            throw new ServiceException("Error in services when retrieving selected user books.", e);
        }
    }
}
