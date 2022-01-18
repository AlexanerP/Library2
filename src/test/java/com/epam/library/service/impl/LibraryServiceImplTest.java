package com.epam.library.service.impl;

import com.epam.library.entity.Library;
import com.epam.library.service.LibraryService;
import com.epam.library.service.ServiceException;
import com.epam.library.service.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceImplTest {

    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
        libraryService = ServiceFactory.getInstance().getLibraryService();
    }

    @Test
    void create() {
        try {
            String city = "Brest";
            String street = "Main street";
            int expected = 1;
            int actual = libraryService.create(city, street);
            assertEquals(expected, actual);
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void showAll() {
        try {
            List<Library> libraries = libraryService.showAll();
            outPut(libraries);
            assertFalse(libraries.isEmpty());
        }catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateStatus() {
        try {
            String status = "opened";
            String libraryId = "1 ";
            boolean condition = libraryService.updateStatus(libraryId, status);
            assertTrue(condition);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void update() {
        try {
            String city = "city";
            String street = "street";
            String libraryId = "1 ";
            int expected = 1;
            int actual = libraryService.update(libraryId, city, street);
            assertEquals(expected, actual);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void showById() {
        try {
            String libraryId ="1 ";
            Optional<Library> optionalLibrary = libraryService.showById(libraryId);
            System.out.println(optionalLibrary.toString());
            assertTrue(optionalLibrary.isPresent());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    void showByCity() {
        try {
            String city = "Минск";
            Optional<Library> optionalLibrary = libraryService.showByCity(city);
//            System.out.println(optionalLibrary.toString());
            assertTrue(optionalLibrary.isPresent());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showByStatus() throws ServiceException {
        List<Library> libraries;
        LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
        String status = "opened";
        libraries = libraryService.showByStatus(status);
        outPut(libraries);
        assertTrue(!libraries.isEmpty());
    }

    private void outPut(List<Library> list) {
        for (Library library : list) {
            System.out.println(library);
        }
    }
}