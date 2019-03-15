package com.nidara.sabanasblancas.gestion.daos.querybuilders;

import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InsertQueryBuilder {

    private String tableName;
    private List<String> fieldNames = new ArrayList<>();
    private List<Object> values = new ArrayList<>();

    public InsertQueryBuilder(String tableName) {
        this.tableName = tableName;
    }

    public InsertQueryBuilder setField(String fieldName, Object value) {
        this.fieldNames.add(fieldName);
        this.values.add(value);
        return this;
    }

    public PreparedStatementCreator build() {
        return connection -> {
            PreparedStatement ps = connection.prepareStatement(generateSql(), Statement.RETURN_GENERATED_KEYS);
            for (int index=0; index< values.size(); index++) {
                ps.setObject(index+1, values.get(index));
            }
            return ps;
        };
    }

    private String generateSql() {

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ");
        builder.append(tableName);
        builder.append(" (");

        StringBuilder valuesBuilder = new StringBuilder();
        valuesBuilder.append(" VALUES (");

        for (String fieldName : fieldNames) {
            builder.append(fieldName);
            builder.append(",");
            valuesBuilder.append("?,");
        }

        builder.deleteCharAt(builder.length()-1);
        builder.append(")");

        valuesBuilder.deleteCharAt(valuesBuilder.length()-1);
        valuesBuilder.append(")");

        builder.append(valuesBuilder);

        return builder.toString();
    }

}
