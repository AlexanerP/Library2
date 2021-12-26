package com.epam.library.service.impl;

import com.epam.library.entity.Library;
import com.epam.library.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class LibraryServiceImpl {

    public List<Library> getLibraries() throws ServiceException {
        System.out.println("Connection DB getLibraries");
        List<Library> libraries = new ArrayList<>();
        libraries.add(new Library("Minsk"));
        libraries.add(new Library("Brest"));
        libraries.add(new Library("Grodno"));
        libraries.add(new Library("Vitebsk"));
        libraries.add(new Library("Gomel"));
        libraries.add(new Library("Mogilev"));
        libraries.add(new Library("Soligorsk"));
        libraries.add(new Library("Bobruisk"));

        return libraries;
    }
}
