package com.nidara.sabanasblancas.gestion.daos.querybuilders;

import static com.nidara.sabanasblancas.gestion.daos.querybuilders.ProductCombinationQueryBuilder.*;

public class ProductPackQueryBuilder extends AbstractQueryBuilder {

    public ProductPackQueryBuilder() {
        addSelectField("p.id_product", ID_PRODUCT);
        addSelectField("p.reference", REFERENCE);
        addSelectField("pl.name", NAME);
        addSelectField("ROUND(p.price*1.21, 2)", PRICE);
        addSelectField("p.weight", WEIGHT);
        addSelectField("IF(p.reference LIKE('%-BL-%'), 'Blanco', '')", COLOR);
        addSelectField("REPLACE(REPLACE(pl.description,'<p>',''),'</p>','')", SIZE);
        addSelectField("1", IS_PACK);

        from("ps_product", "p");
        addJoin("JOIN ps_product_lang pl ON p.id_product=pl.id_product AND pl.id_lang=1");

        addWhere("p.active=1");
        addWhere("p.id_product IN (SELECT DISTINCT(id_product_pack) FROM ps_pack)");
    }

    public ProductPackQueryBuilder withStock() {
        addSelectField("st.quantity", CURRENT_STOCK);
        addJoin("JOIN ps_stock_available st ON p.id_product=st.id_product");
        return this;
    }

    public ProductPackQueryBuilder withCategory(int idCategory) {
        WithCategoryClauseHelper clauseBuilder = WithCategoryClauseHelper.getInstance();
        addWhere(clauseBuilder.getWhereClauseForCategory(idCategory));
        return this;
    }

    public String build() {
        return buildQuery();
    }
}
