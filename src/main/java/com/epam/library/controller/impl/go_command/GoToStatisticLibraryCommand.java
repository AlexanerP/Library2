package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.CommandType;
import com.epam.library.controller.PathJsp;
import com.epam.library.entity.LoanCardStatus;
import com.epam.library.entity.OrderStatus;
import com.epam.library.entity.UserStatus;
import com.epam.library.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToStatisticLibraryCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToStatisticLibraryCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Getting statistics library");
            req.getSession().setAttribute("url", "Controller?command=" + CommandType.GO_TO_STATISTICS);
            UserService userService = ServiceFactory.getInstance().getUserService();
            BookService bookService = ServiceFactory.getInstance().getBookService();
            LoanCardService loanCardService = ServiceFactory.getInstance().getLoanCardService();
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
            GenreService genreService = ServiceFactory.getInstance().getGenreService();

            long countBooks = bookService.getCountBooks();

            long countLoanCardOpen = loanCardService.showCountCards(LoanCardStatus.OPEN.name());
            long countLoanCardClosed = loanCardService.showCountCards(LoanCardStatus.CLOSED.name());
            long countAuthor = authorService.getCountAuthors();
            long countGenre = genreService.getCountGenres();

            long ordersOpen = orderService.showCountByStatus(OrderStatus.OPENED.name());
            long ordersApproved = orderService.showCountByStatus(OrderStatus.APPROVED.name());
            long ordersRejected = orderService.showCountByStatus(OrderStatus.REJECTED.name());
            long ordersArrived = orderService.showCountByStatus(OrderStatus.ARRIVED.name());
            long ordersClosed = orderService.showCountByStatus(OrderStatus.CLOSED.name());

            long usersBlocked = userService.showCountByStatus(UserStatus.BLOCKED.name());
            long usersActive = userService.showCountByStatus(UserStatus.ACTIVE.name());
            long usersDelete = userService.showCountByStatus(UserStatus.DELETE.name());
            long countUsers = usersActive + usersBlocked + usersDelete;

            req.setAttribute("countUsers", countUsers);
            req.setAttribute("countBooks", countBooks);

            req.setAttribute("countUsersBlocked", usersBlocked);
            req.setAttribute("countUsersDelete", usersDelete);
            req.setAttribute("countUsersActive", usersActive);

            req.setAttribute("countLoanCardOpen", countLoanCardOpen);
            req.setAttribute("countLoanCard", countLoanCardClosed + countLoanCardOpen);

            req.setAttribute("countAuthor", countAuthor);
            req.setAttribute("countGenre", countGenre);

            req.setAttribute("ordersOpen", ordersOpen);
            req.setAttribute("ordersApproved", ordersApproved);
            req.setAttribute("ordersRejected", ordersRejected);
            req.setAttribute("ordersArrived", ordersArrived);
            req.setAttribute("ordersClosed", ordersClosed);

            req.getRequestDispatcher(PathJsp.STATISTICS_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while getting library statistics", e);
            resp.sendRedirect(PathJsp.ERROR_PAGE);
        }
    }
}
