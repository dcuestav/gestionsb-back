package com.nidara.sabanasblancas.gestion.daos.rowmappers;

import com.nidara.sabanasblancas.gestion.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    public static final String ID_PRODUCT = "id_product";
    public static final String ID_PRODUCT_ATTRIBUTE = "id_product_attribute";
    public static final String REFERENCE = "reference";
    public static final String NAME = "name";
    public static final String COLOR = "color";
    public static final String SIZE = "size";
    public static final String PRICE = "price";
    public static final String COST = "cost";
    public static final String WEIGHT = "weight";
    public static final String CURRENT_STOCK = "currentStock";
    public static final String IS_PACK = "is_pack";

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
        product.setCurrentStock(getInteger(resultSet, CURRENT_STOCK, null));
        product.setPack(getBoolean(resultSet, IS_PACK, false));

        if (product.getPack()) {
            ProductPackNameResolver nameResolver = ProductPackNameResolver.getInstance();
            product.setName(nameResolver.getNameFromReference(product.getReference(), product.getName()));
        }

        return product;
    }

    private Integer getInteger(ResultSet resultSet, String field, Integer defaultValue) {
        try {
            return resultSet.getInt(field);
        } catch(SQLException e) {
            return defaultValue;
        }
    }

    private String getString(ResultSet resultSet, String field, String defaultValue) {
        try {
            return resultSet.getString(field);
        } catch(SQLException e) {
            return defaultValue;
        }
    }

    private Boolean getBoolean(ResultSet resultSet, String field, Boolean defaultValue) {
        try {
            return resultSet.getBoolean(field);
        } catch(SQLException e) {
            return defaultValue;
        }
    }

    private BigDecimal getBigDecimal(ResultSet resultSet, String field, BigDecimal defaultValue) {
        try {
            return resultSet.getBigDecimal(field);
        } catch(SQLException e) {
            return defaultValue;
        }
    }

}
