package com.nidara.sabanasblancas.gestion.daos.querybuilders;

abstract class AbastractQueryBuilder {

    private StringBuilder selectBuilder = new StringBuilder();
    private StringBuilder fromBuilder = new StringBuilder();
    private StringBuilder whereBuilder = new StringBuilder();
    private StringBuilder orderBuilder = new StringBuilder();

    private Integer page;
    private int size = 10;

    void addSelectField(String expression, String alias) {
        if (selectBuilder.length()==0) {
            selectBuilder.append("SELECT ");
        } else {
            selectBuilder.append(", ");
        }
        selectBuilder.append(expression);
        selectBuilder.append(" as '");
        selectBuilder.append(alias);
        selectBuilder.append("' ");
    }

    void from(String table, String alias) {
        fromBuilder.append(" FROM ");
        fromBuilder.append(table);
        fromBuilder.append(" ");
        fromBuilder.append(alias);
        fromBuilder.append(" ");
    }

    void addJoin(String expression) {

    }

    void addWhere(String expression) {
        if (whereBuilder.length()==0) {
            whereBuilder.append(" WHERE (");
        } else {
            whereBuilder.append(" AND (");
        }
        whereBuilder.append(expression);
        whereBuilder.append(") ");
    }

    void setPage(int page) {
        this.page = page;
    }

    void setPage(int page, int size) {
        this.page = page;
        this.size = size;
    }

    void setOrderBy(String expression, QueryOrder order) {
        if (orderBuilder.length()==0) {
            orderBuilder.append(" ORDER BY ");
        } else {
            orderBuilder.append(", ");
        }
        orderBuilder.append(expression);
        if (order!=null) {
            orderBuilder.append(" ");
            orderBuilder.append(order.toString());
            orderBuilder.append(" ");
        }
    }

    String buildQuery() {
        StringBuilder queryBuilder = new StringBuilder(selectBuilder);

        queryBuilder.append(fromBuilder);
        queryBuilder.append(whereBuilder);
        queryBuilder.append(orderBuilder);

        if (page!=null) {
            queryBuilder.append(" LIMIT ");
            queryBuilder.append(size);
            queryBuilder.append(" OFFSET ");
            queryBuilder.append(page*size);
        }

        return queryBuilder.toString();
    }

}
