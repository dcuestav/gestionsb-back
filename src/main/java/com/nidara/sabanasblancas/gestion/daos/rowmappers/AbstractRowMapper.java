package com.nidara.sabanasblancas.gestion.daos.rowmappers;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public abstract class AbstractRowMapper<T> implements RowMapper<T> {

    Integer getInteger(ResultSet resultSet, String field, Integer defaultValue) {
        try {
            return resultSet.getInt(field);
        } catch(SQLException e) {
            return defaultValue;
        }
    }

    String getString(ResultSet resultSet, String field, String defaultValue) {
        try {
            return resultSet.getString(field);
        } catch(SQLException e) {
            return defaultValue;
        }
    }

    Boolean getBoolean(ResultSet resultSet, String field, Boolean defaultValue) {
        try {
            return resultSet.getBoolean(field);
        } catch(SQLException e) {
            return defaultValue;
        }
    }

    BigDecimal getBigDecimal(ResultSet resultSet, String field, BigDecimal defaultValue) {
        try {
            return resultSet.getBigDecimal(field);
        } catch(SQLException e) {
            return defaultValue;
        }
    }

    Date getDate(ResultSet resultSet, String field, Date defaultValue) {
        try {
            return resultSet.getDate(field);
        } catch(SQLException e) {
            return defaultValue;
        }
    }

}
