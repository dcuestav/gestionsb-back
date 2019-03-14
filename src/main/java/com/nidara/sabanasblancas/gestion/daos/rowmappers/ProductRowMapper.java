package com.nidara.sabanasblancas.gestion.daos.rowmappers;

import com.nidara.sabanasblancas.gestion.model.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.nidara.sabanasblancas.gestion.daos.querybuilders.ProductCombinationQueryBuilder.*;

public class ProductRowMapper extends AbstractRowMapper<Product> {

    public static final String UNKNOWN = "???";

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setIdProduct(getInteger(resultSet, ID_PRODUCT, null));
        product.setIdProductAttribute(getInteger(resultSet, ID_PRODUCT_ATTRIBUTE, 0));
        product.setReference(getString(resultSet, REFERENCE, UNKNOWN));
        product.setName(getString(resultSet, NAME, UNKNOWN));
        product.setColor(getString(resultSet, COLOR, UNKNOWN));
        product.setSize(getString(resultSet, SIZE, UNKNOWN));
        product.setPrice(getBigDecimal(resultSet, PRICE, BigDecimal.ZERO));
        product.setCost(getBigDecimal(resultSet, COST, BigDecimal.ZERO));
        product.setWeight(getBigDecimal(resultSet, WEIGHT, BigDecimal.ZERO));
        product.setIdStock(getInteger(resultSet, ID_STOCK, null));
        product.setCurrentStock(getInteger(resultSet, CURRENT_STOCK, null));
        product.setPack(getBoolean(resultSet, IS_PACK, false));

        if (product.getPack()) {
            ProductPackNameResolver nameResolver = ProductPackNameResolver.getInstance();
            product.setName(nameResolver.getNameFromReference(product.getReference(), product.getName()));
        }

        return product;
    }

}
