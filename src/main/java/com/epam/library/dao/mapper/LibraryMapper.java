package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.Library;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryMapper implements RowMapper<Library> {

    @Override
    public Library map(ResultSet resultSet) throws SQLException {
        Library library = new Library();
        library.setLibraryId(resultSet.getInt(ColumnName.LIBRARY_ID_LIBRARY));
        library.setCity(resultSet.getString(ColumnName.LIBRARY_CITY));
        library.setStreet(resultSet.getString(ColumnName.LIBRARY_STREET));
        return library;
    }
}
