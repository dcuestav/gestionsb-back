package com.nidara.sabanasblancas.gestion.daos.querybuilders;

import java.util.Collection;

public class ProductCombinationQueryBuilder extends AbstractQueryBuilder {

    public static final String ID_PRODUCT = "id_product";
    public static final String ID_PRODUCT_ATTRIBUTE = "id_product_attribute";
    public static final String REFERENCE = "reference";
    public static final String NAME = "name";
    public static final String COLOR = "color";
    public static final String SIZE = "size";
    public static final String PRICE = "price";
    public static final String COST = "cost";
    public static final String WEIGHT = "weight";
    public static final String ID_STOCK = "idStock";
    public static final String CURRENT_STOCK = "currentStock";
    public static final String IS_PACK = "is_pack";

    public ProductCombinationQueryBuilder() {
        addSelectField("p.id_product", ID_PRODUCT);
        addSelectField("pa.id_product_attribute", ID_PRODUCT_ATTRIBUTE);
        addSelectField("pa.reference", REFERENCE);
        addSelectField("pl.name", NAME);
        addSelectField("ROUND(pa.price*1.21, 2)", PRICE);
        addSelectField("pa.weight", WEIGHT);
        addSelectField("0", IS_PACK);

        from("ps_product", "p");
        addJoin("JOIN ps_product_attribute pa USING(id_product)");
        addJoin("JOIN ps_product_lang pl ON p.id_product=pl.id_product AND pl.id_lang=1");

        addWhere("p.active=1");
    }

    public ProductCombinationQueryBuilder withColorAndSize() {
        addSelectField("color.name", COLOR);
        addSelectField("size.name", SIZE);

        addJoin("JOIN ps_product_attribute_combination pac USING(id_product_attribute)");
        addJoin("JOIN ps_attribute att ON pac.id_attribute=att.id_attribute AND att.color=''");
        addJoin("JOIN ps_attribute_lang size ON att.id_attribute=size.id_attribute AND size.id_lang=1");

        addJoin("JOIN ps_product_attribute_combination pac2 USING(id_product_attribute)");
        addJoin("JOIN ps_attribute att2 ON pac2.id_attribute=att2.id_attribute AND att2.color<>''");
        addJoin("JOIN ps_attribute_lang color ON att2.id_attribute=color.id_attribute AND color.id_lang=1");
        return this;
    }

    public ProductCombinationQueryBuilder withStock() {
        addSelectField("st.id_stock_available", ID_STOCK);
        addSelectField("st.quantity", CURRENT_STOCK);
        addJoin("JOIN ps_stock_available st ON p.id_product=st.id_product AND st.id_product_attribute=pa.id_product_attribute");
        return this;
    }

    public ProductCombinationQueryBuilder withCost() {
        addSelectField("psup.product_supplier_price_te", COST);
        addJoin("JOIN ps_product_supplier psup ON p.id_product=psup.id_product AND pa.id_product_attribute=psup.id_product_attribute");
        return this;
    }

    public ProductCombinationQueryBuilder withCategory(int idCategory) {
        WithCategoryClauseHelper clauseBuilder = WithCategoryClauseHelper.getInstance();
        addWhere(clauseBuilder.getWhereClauseForCategory(idCategory));
        return this;
    }

    public ProductCombinationQueryBuilder withStockIdIn(Collection<Integer> stockIds) {
        addWhere("st.id_stock_available IN " + intArrayToInClause(stockIds));
        return this;
    }

    public String build() {
        return buildQuery();
    }
}
