package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.Library;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.service.LibraryService;
import com.epam.library.service.OrderDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderCatalogCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(OrderCatalogCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OrderDtoService orderDtoService = ServiceFactory.getInstance().getOrderDtoService();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            String userId = req.getParameter("userId");
            String orderId = req.getParameter("orderId");
            String city = req.getParameter("city");
            String status = req.getParameter("status");
            String allOrders = req.getParameter("allOrders");
  System.out.println("userId/" + userId + ". orderId/" + orderId + ". city/" + city + ". status/" + status + ". allOrders/" + allOrders);
            List<OrderDto> orders = new ArrayList<>();

            List<Library> libraries = libraryService.showAll();

            if (userId != null) {
System.out.println("UserId ");
                orders = orderDtoService.showOrdersUser(userId);
            }else if (orderId != null) {
 System.out.println("orderId ");
                orders.add(orderDtoService.showOrderById(orderId).get());
            }else if (city != null && status == null) {
System.out.println("city  status=null");
                orders = orderDtoService.showOrdersByCity(city);
            }else if (status != null && city == null) {
System.out.println("city == null status");
                orders = orderDtoService.showOrdersByStatus(status);
            }else if (city != null  && status != null) {
System.out.println("city status");
                orders = orderDtoService.showOrdersByCityAndStatus(city, status);
            } else if (allOrders != null) {
System.out.println("allOrders ");
                orders = orderDtoService.showAllOrders();
            }
            req.setAttribute("libraries", libraries);
            req.setAttribute("ordersSize", orders.size());
            req.setAttribute("orders", orders);
            req.getRequestDispatcher(PathFile.ORDERS_CATALOG_PAGE).forward(req, resp);
        } catch (ServiceException e) {
            logger.error("Error while working with the order catalog.");
            e.printStackTrace();
//            resp.sendRedirect(PathFile.ERROR_PAGE);
        }
    }
}
