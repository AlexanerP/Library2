package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.Library;
import com.epam.library.entity.LibraryStatus;
import com.epam.library.entity.OrderStatus;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.service.LibraryService;
import com.epam.library.service.OrderDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GiveOutBookCatalogCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            OrderDtoService orderDtoService = ServiceFactory.getInstance().getOrderDtoService();
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<OrderDto> orders = new ArrayList<>();
            List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());

            String orderId = req.getParameter("orderId");
            String city = req.getParameter("city");
            String all = req.getParameter("getAll");

            System.out.println("OrderId/" + orderId + ". city/" + city + ". getAll/" + all);

            if (orderId != null && orderId != "") {
    System.out.println("orderId");
                orders.add(orderDtoService.showOrderById(1).get());
            } else if (city != null && city != "") {
    System.out.println("city");
                orders = orderDtoService.showOrdersByCityAndStatus(city, OrderStatus.APPROVED);
            } else if (all != null && all != "") {
    System.out.println("all");
                orders = orderDtoService.showOrdersByStatus(OrderStatus.APPROVED);
            }

            req.setAttribute("libraries", libraries);
            req.setAttribute("orders", orders);
            req.setAttribute("ordersSize", orders.size());
            req.getRequestDispatcher(PathFile.GIVE_OUT_BOOK_PAGE).forward(req, resp);

        } catch (ServiceException e) {

        }
    }
}
