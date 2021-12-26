package com.epam.library.dao.mapper;

import com.epam.library.dao.constant.ColumnName;
import com.epam.library.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusMapper implements RowMapper<Status> {

    @Override
    public Status map(ResultSet resultSet) throws SQLException {
        Status status = new Status();
        status.setStatusId(resultSet.getInt(ColumnName.STATUS_ID_STATUS));
        status.setValue(resultSet.getString(ColumnName.STATUS_STATUS));
        return status;
    }
}
