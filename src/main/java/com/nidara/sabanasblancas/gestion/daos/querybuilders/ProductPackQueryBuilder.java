package com.nidara.sabanasblancas.gestion.daos.querybuilders;

public class ProductPackQueryBuilder {

    private String sql = "SELECT p.id_product as id_product, " +
            "p.reference as reference, " +
            "pl.name as 'name'," +
            "ROUND(p.price*1.21, 2) as price, " +
            "p.weight as weight, " +
            "IF(p.reference LIKE('%-BL-%'), 'Blanco', '') as 'color', " +
            "REPLACE(REPLACE(pl.description,'<p>',''),'</p>','') as size, " +
            "1 as is_pack ";

    private String from = "FROM ps_product p " +
            "JOIN ps_product_lang pl ON p.id_product=pl.id_product AND pl.id_lang=1 ";

    // Stock

    private String stockFields = ", st.quantity as currentStock ";

    private String stockJoin = "JOIN ps_stock_available st ON p.id_product=st.id_product ";

    private String where = " WHERE p.active=1 " +
            "AND p.id_product IN (SELECT DISTINCT(id_product_pack) FROM ps_pack) ";

    private boolean stock = false;

    public ProductPackQueryBuilder() {
    }

    public ProductPackQueryBuilder withStock() {
        stock = true;
        return this;
    }

    public ProductPackQueryBuilder withCategory(int idCategory) {
        WithCategoryClauseHelper clauseBuilder = WithCategoryClauseHelper.getInstance();
        where += " AND " + clauseBuilder.getWhereClauseForCategory(idCategory);
        return this;
    }

    public String build() {
        return sql + (stock ? stockFields : "")
                + from + (stock ? stockJoin : "")
                + where;
    }
}
