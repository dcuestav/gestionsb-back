package com.nidara.sabanasblancas.gestion.daos.querybuilders;

public class ProductCombinationQueryBuilder {

    private String sql = "SELECT p.id_product as id_product, " +
            "pa.id_product_attribute as id_product_attribute, " +
            "pa.reference as reference, " +
            "pl.name as name, " +
            "ROUND(pa.price*1.21, 2) as price, " +
            "pa.weight as weight, " +
            "0 as is_pack ";

    private String from = "FROM ps_product p " +
            "JOIN ps_product_attribute pa USING(id_product) " +
            "JOIN ps_product_lang pl ON p.id_product=pl.id_product AND pl.id_lang=1 ";

    // Color and size

    private String colorAndSizeFields = ", color.name as color, " +
            "size.name as size ";

    private String colorAndSizeJoin = "JOIN ps_product_attribute_combination pac USING(id_product_attribute) " +
            "JOIN ps_attribute att ON pac.id_attribute=att.id_attribute AND att.color='' " +
            "JOIN ps_attribute_lang size ON att.id_attribute=size.id_attribute AND size.id_lang=1 " +

            "JOIN ps_product_attribute_combination pac2 USING(id_product_attribute) " +
            "JOIN ps_attribute att2 ON pac2.id_attribute=att2.id_attribute AND att2.color<>'' " +
            "JOIN ps_attribute_lang color ON att2.id_attribute=color.id_attribute AND color.id_lang=1 ";

    // Stock

    private String stockFields = ", st.quantity as currentStock ";

    private String stockJoin = "JOIN ps_stock_available st ON p.id_product=st.id_product AND st.id_product_attribute=pa.id_product_attribute ";

    // Cost

    private String costFields = ", psup.product_supplier_price_te as cost ";

    private String costJoin = "JOIN ps_supplier sup ON p.id_supplier=sup.id_supplier " +
            "JOIN ps_product_supplier psup ON p.id_product=psup.id_product AND pa.id_product_attribute=psup.id_product_attribute";

    private String where = " WHERE p.active=1 ";

    private boolean colorAndSize = false;
    private boolean stock = false;
    private boolean cost = false;

    public ProductCombinationQueryBuilder() {
    }

    public ProductCombinationQueryBuilder withColorAndSize() {
        colorAndSize = true;
        return this;
    }

    public ProductCombinationQueryBuilder withStock() {
        stock = true;
        return this;
    }

    public ProductCombinationQueryBuilder withCost() {
        cost = true;
        return this;
    }

    public ProductCombinationQueryBuilder withCategory(int idCategory) {
        WithCategoryClauseHelper clauseBuilder = WithCategoryClauseHelper.getInstance();
        where += " AND " + clauseBuilder.getWhereClauseForCategory(idCategory);
        return this;
    }

    public String build() {
        return sql + (colorAndSize ? colorAndSizeFields : "") + (stock ? stockFields : "") + (cost ? costFields : "")
                + from + (colorAndSize ? colorAndSizeJoin : "") + (stock ? stockJoin : "") + (cost ? costJoin : "")
                + where;
    }
}
