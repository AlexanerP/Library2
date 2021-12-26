package com.epam.library.controller.impl.go_command;

import com.epam.library.controller.Command;
import com.epam.library.entity.dto.BookDto;
import com.epam.library.service.BookDtoService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.impl.BookDtoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GOTOTest implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("GOTOTest");
        List<BookDto> bookDtos = new ArrayList<>();
        BookDtoService bookDtoService = new BookDtoServiceImpl();
        try {
            bookDtos = bookDtoService.showCatalog();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.setAttribute("books", bookDtos);

        req.getRequestDispatcher("WEB-INF/pages/hello.jsp").forward(req, resp);
    }
}
