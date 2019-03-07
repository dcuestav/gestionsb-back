package com.nidara.sabanasblancas.gestion.daos.querybuilders;

import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class UpdateQueryBuilder {

    private String tableName;
    private List<String> fieldNames = new ArrayList<>();
    private List<Object> values = new ArrayList<>();
    private String whereField;
    private int idWhereValue;

    public UpdateQueryBuilder(String tableName) {
        this.tableName = tableName;
    }

    public UpdateQueryBuilder setField(String fieldName, Object value) {
        this.fieldNames.add(fieldName);
        this.values.add(value);
        return this;
    }

    public UpdateQueryBuilder whereIdField(String whereField, int id) {
        this.whereField = whereField;
        this.idWhereValue = id;
        return this;
    }

    public PreparedStatementCreator build() {
        return connection -> {
            PreparedStatement ps = connection.prepareStatement(generateSql());
            for (int index=0; index< values.size(); index++) {
                ps.setObject(index, values.get(index));
            }
            ps.setInt(values.size(), idWhereValue);
            return ps;
        };
    }

    private String generateSql() {

        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ");
        builder.append(tableName);
        builder.append(" SET ");

        for (String fieldName : fieldNames) {
            builder.append(fieldName);
            builder.append("=?, ");
        }

        builder.deleteCharAt(builder.length()-1);
        builder.deleteCharAt(builder.length()-1);

        builder.append("WHERE ");

        builder.append(whereField);
        builder.append("=?");

        return builder.toString();
    }

}
