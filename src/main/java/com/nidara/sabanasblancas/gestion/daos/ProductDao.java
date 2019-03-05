package com.nidara.sabanasblancas.gestion.daos;

import com.nidara.sabanasblancas.gestion.daos.querybuilders.ProductCombinationQueryBuilder;
import com.nidara.sabanasblancas.gestion.daos.querybuilders.ProductPackQueryBuilder;
import com.nidara.sabanasblancas.gestion.daos.rowmappers.ProductRowMapper;
import com.nidara.sabanasblancas.gestion.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<Product> getProductCombinationsByCategory(int idCategory) {
        String sql = new ProductCombinationQueryBuilder()
                .withColorAndSize()
                .withCost()
                .withStock()
                .withCategory(idCategory)
                .build();

        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    private List<Product> getProductPacksByCategory(int idCategory) {
        String sql = new ProductPackQueryBuilder()
                .withStock()
                .withCategory(idCategory)
                .build();

        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    public List<Product> getByCategory(int idCategory) {

        List<Product> productCombinations = getProductCombinationsByCategory(idCategory);

        List<Product> productPacks = getProductPacksByCategory(idCategory);
        productCombinations.addAll(productPacks);

        return productCombinations;
    }

    public List<Product> getAll() {

        String combinationsSql = new ProductCombinationQueryBuilder()
                .withColorAndSize()
                .build();

        String packsSql = new ProductPackQueryBuilder()
                .build();

        List<Product> productCombinations = jdbcTemplate.query(combinationsSql, new ProductRowMapper());
        List<Product> productPacks = jdbcTemplate.query(packsSql, new ProductRowMapper());

        productCombinations.addAll(productPacks);

        return productCombinations;
    }
}
