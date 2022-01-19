package com.epam.library.service.impl;

import com.epam.library.dao.DaoException;
import com.epam.library.dao.DaoFactory;
import com.epam.library.dao.OrderDtoDao;
import com.epam.library.entity.Library;
import com.epam.library.entity.OrderStatus;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class OrderDtoServiceImpl implements OrderDtoService {

    private static final Logger logger = LoggerFactory.getLogger(OrderDtoServiceImpl.class);

    @Override
    public List<OrderDto> showOrdersUser(String userId) throws ServiceException {
        try {
            OrderDtoDao orderDtoDao = DaoFactory.getInstance().getOrderBookDtoDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (userId != null) {
                if (validator.isNumber(userId)) {
                    return orderDtoDao.getOrderByUser(Long.parseLong(userId.trim()));
                } else {
                    throw new ServiceException("Invalid user ID");
                }
            } else {
                throw new ServiceException("The user ID value is empty.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when receiving user orders.");
            throw new ServiceException("Error in services when receiving user orders.", e);
        }
    }

    @Override
    public Optional<OrderDto> showOrderById(String orderId) throws ServiceException {
        try {
            OrderDtoDao orderDtoDao = DaoFactory.getInstance().getOrderBookDtoDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            if (orderId != null) {
                if (validator.isNumber(orderId)) {
                    return orderDtoDao.getOrderById(Long.parseLong(orderId.trim()));
                } else {
                    throw new ServiceException("Invalid order ID");
                }
            } else {
                throw new ServiceException("The order ID value is empty.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when receiving an order by ID.");
            throw new ServiceException("Error in services when receiving an order by ID.", e);
        }
    }

    @Override
    public List<OrderDto> showAllOrders() throws ServiceException {
        try {
            OrderDtoDao orderDtoDao = DaoFactory.getInstance().getOrderBookDtoDao();
            return orderDtoDao.getOrders();

        }catch (DaoException e) {
            logger.error("Error in services when receiving all orders.");
            throw new ServiceException("Error in services when receiving all orders.", e);
        }
    }

    @Override
    public List<OrderDto> showOrdersByStatus(String status) throws ServiceException {
        try {
            OrderDtoDao orderDtoDao = DaoFactory.getInstance().getOrderBookDtoDao();
            if (status != null) {
                if (status.equalsIgnoreCase(OrderStatus.OPENED.name()) || status.equalsIgnoreCase(OrderStatus.APPROVED.name())
                        || status.equalsIgnoreCase(OrderStatus.ARRIVED.name()) || status.equalsIgnoreCase(OrderStatus.REJECTED.name())) {
                    return orderDtoDao.getOrderByStatus(OrderStatus.valueOf(status.toUpperCase()));
                } else {
                    throw new ServiceException("Invalid order status.");
                }
            } else {
                throw new ServiceException("The status value is empty.");
            }
        }catch (DaoException e) {
            logger.error("Error in services when receiving all orders by status.");
            throw new ServiceException("Error in services when receiving all orders by status.", e);
        }
    }

    @Override
    public List<OrderDto> showOrdersByCityAndStatus(String city, String status) throws ServiceException {
        try {
            OrderDtoDao orderDtoDao = DaoFactory.getInstance().getOrderBookDtoDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            if (city != null && status != null) {
                if (validator.isLength(city)) {
                    Optional<Library> optionalLibrary = libraryService.showByCity(city);
                    if (optionalLibrary.isPresent()) {
                        if (status.equalsIgnoreCase(OrderStatus.OPENED.name())
                                || status.equalsIgnoreCase(OrderStatus.APPROVED.name())
                                || status.equalsIgnoreCase(OrderStatus.ARRIVED.name())
                                || status.equalsIgnoreCase(OrderStatus.REJECTED.name())) {
                            return orderDtoDao.getOrderByCityAndStatus(city,
                                    OrderStatus.valueOf(status.toUpperCase()));
                        } else {
                            throw new ServiceException("Invalid order status.");
                        }
                    } else {
                        throw new ServiceException("Invalid city value. City is missing from the database.");
                    }
                } else {
                    throw new ServiceException("Invalid city value. The word is too long.");
                }
            }else {
                throw new ServiceException("The status or the city value is empty.");
            }
        } catch (DaoException e) {
            logger.error("Error in services when receiving orders by city and status.");
            throw new ServiceException("Error in services when receiving orders by city and status.", e);
        }
    }

    @Override
    public List<OrderDto> showOrdersByCity(String city) throws ServiceException {
        try {
            OrderDtoDao orderDtoDao = DaoFactory.getInstance().getOrderBookDtoDao();
            ServiceValidator validator = ServiceFactory.getInstance().getServiceValidator();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            if (city != null) {
                Optional<Library> optionalLibrary = libraryService.showByCity(city);
                if (optionalLibrary.isPresent()) {
                    return orderDtoDao.getOrderByCity(city);
                } else {
                    throw new ServiceException("Invalid city value. City is missing from the database.");
                }
            }else {
                throw new ServiceException("The city value is empty.");
            }
        } catch (DaoException e) {
            logger.error("Error in services when receiving orders by city.");
            throw new ServiceException("Error in services when receiving orders by city.", e);
        }
    }
}
