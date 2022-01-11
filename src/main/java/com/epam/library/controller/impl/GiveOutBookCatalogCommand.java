package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GiveOutBookCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GiveOutBookCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.GIVE_OUT_BOOK_USER);
            OrderDtoService orderDtoService = ServiceFactory.getInstance().getOrderDtoService();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<OrderDto> orders = new ArrayList<>();
            List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());

            String orderId = req.getParameter("orderId");
            String city = req.getParameter("library");
            String status = req.getParameter("status");
            String all = req.getParameter("getAll");

            if (orderId != null) {
                orders.add(orderDtoService.showOrderById(orderId).get());
            } else if (city != null && status == null) {
                orders = orderDtoService.showOrdersByCity(city);
            } else if (city != null && status != null) {
                orders = orderDtoService.showOrdersByCityAndStatus(city, status);
            }else if (all != null) {
                orders = orderDtoService.showAllOrders();
            }

            req.setAttribute("libraries", libraries);
            req.setAttribute("orders", orders);
            req.setAttribute("ordersSize", orders.size());
            req.getRequestDispatcher(PathJsp.GIVE_OUT_BOOK_PAGE).forward(req, resp);

        } catch (ServiceException e) {
            logger.error("An error occurred when working with the catalog for issuing books.", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
