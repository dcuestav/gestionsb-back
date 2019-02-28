package com.nidara.sabanasblancas.gestion;

import com.nidara.sabanasblancas.gestion.daos.querybuilders.ProductCombinationQueryBuilder;
import com.nidara.sabanasblancas.gestion.daos.querybuilders.ProductPackQueryBuilder;
import com.nidara.sabanasblancas.gestion.daos.rowmappers.ProductRowMapper;
import com.nidara.sabanasblancas.gestion.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JDBCTemplateTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sql = "SELECT id_product FROM ps_product WHERE id_product=?";

    @Test
    public void test_database_connection() {

        int PRODUCT_ID = 31;

        Product product = jdbcTemplate.queryForObject(sql, new Object[] { PRODUCT_ID }, new ProductRowMapper());

        Assert.assertEquals(true, product.getIdProduct().equals(PRODUCT_ID));
    }

    @Test
    public void test_product_by_category() {

        String sql = new ProductCombinationQueryBuilder()
                .withColorAndSize()
                .withCost()
                .withStock()
                .withCategory(16)
                .build();

        List<Product> products = jdbcTemplate.query(sql, new ProductRowMapper()); // Protectores

        Assert.assertFalse(products.isEmpty());
    }

    @Test
    public void test_product_pack() {

        String sql = new ProductPackQueryBuilder()
                .withStock()
                .withCategory(41)
                .build();

        List<Product> productPacks = jdbcTemplate.query(sql, new ProductRowMapper()); // Protectores

        Assert.assertFalse(productPacks.isEmpty());
    }

}
