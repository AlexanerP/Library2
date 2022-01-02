package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.controller.PathFile;
import com.epam.library.entity.*;
import com.epam.library.service.*;
import com.epam.library.service.impl.BookServiceImpl;
import com.epam.library.service.impl.GenreServiceImpl;
import com.epam.library.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToMainPageCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(GoToMainPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Go to main page command.");
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            GenreService genreService = ServiceFactory.getInstance().getGenreService();
            AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
//            UserService userService = ServiceFactory.getInstance().getUserService();
            BookService bookService = ServiceFactory.getInstance().getBookService();
//            OrderService orderService = ServiceFactory.getInstance().getOrderService();

            List<Genre> genres = genreService.getGenres();
//            long countUsers = userService.getCountUsers();
            long countBooks = bookService.getCountBooks();
            int countAuthors = authorService.getCountAuthors();
            int countGenres = genreService.countGenres();
//            int countOrders = orderService.getCountOrders();
//
//            List<User> usersBlocked = userService.showUserByStatus(UserStatus.BLOCKED);
//            List<User> usersActive = userService.showUserByStatus(UserStatus.ACTIVE);
//
//            req.setAttribute("countUsers", countUsers);
            req.setAttribute("countBooks", countBooks);
            req.setAttribute("countAuthors", countAuthors);
            req.setAttribute("countGenres", countGenres);
//            req.setAttribute("countOrders", countOrders);
//            req.setAttribute("countUsersBlocked", usersBlocked.size());
//            req.setAttribute("countUsersActive", usersActive.size());
            LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
            List<Library> libraries = libraryService.showByStatus(LibraryStatus.OPENED.name());
            req.setAttribute("libraries", libraries);
            session.setAttribute("user", user);
            req.setAttribute("genres", genres);

            req.getRequestDispatcher(PathFile.MAIN_PAGE).forward(req, resp);
        }catch (ServiceException e) {
            logger.error("Error while executing the command GoTo Main Command error during command execution", e);

        }
    }
}
