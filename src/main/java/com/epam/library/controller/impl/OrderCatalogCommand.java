package com.epam.library.controller.impl;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.Library;
import com.epam.library.entity.OrderStatus;
import com.epam.library.entity.dto.OrderDto;
import com.epam.library.service.OrderDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import com.epam.library.service.impl.LibraryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderCatalogCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RequestsCatalog");
        try {
            OrderDtoService orderDtoService = ServiceFactory.getInstance().getOrderDtoService();
            String userId = req.getParameter("userId");
            String orderId = req.getParameter("orderId");
            String city = req.getParameter("city");
            String status = req.getParameter("status");
            String allRequest = req.getParameter("allRequest");
  System.out.println("userId/" + userId + ". orderId/" + orderId + ". city/" + city + ". status/" + status + ". allRequest/" + allRequest);
            List<OrderDto> orders = new ArrayList<>();

            List<Library> cities = null;
            LibraryServiceImpl libraryService = new LibraryServiceImpl();
            cities = libraryService.showAll();

            if (userId != null && userId != "") {
System.out.println("UserId ");
                orders = orderDtoService.showOrdersUser(Long.parseLong(userId));
            }else if (orderId != null && orderId != "") {
 System.out.println("orderId ");
                orders.add(orderDtoService.showOrderById(Long.parseLong(orderId)).get());
            }else if (city != null && city != "" && status == null || status == "") {
System.out.println("city  status=null");
                orders = orderDtoService.showOrdersByCityAndStatus(city, null);
            }else if (status != null && status != "" && city == null) {
System.out.println("city == null status");
                orders = orderDtoService.showOrdersByCityAndStatus(null, OrderStatus.valueOf(status.toUpperCase()));
            }else if (city != null && city != "" && status != null && status != "") {
System.out.println("city status");
                orders = orderDtoService.showOrdersByCityAndStatus(city, OrderStatus.valueOf(status.toUpperCase()));
            } else if (allRequest != null && allRequest != "") {
System.out.println("allRequest ");
                orders = orderDtoService.getOrders();
            }

            req.setAttribute("cities", cities);
            req.setAttribute("ordersSize", orders.size());
            req.setAttribute("orders", orders);
//            resp.sendRedirect("?command=GoToOrderCatalog");
            req.getRequestDispatcher(PathFile.ORDERS_CATALOG_PAGE).forward(req, resp);
        } catch (ServiceException e) {

        }
    }
}
