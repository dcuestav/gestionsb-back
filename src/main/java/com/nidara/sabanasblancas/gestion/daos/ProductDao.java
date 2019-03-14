package com.nidara.sabanasblancas.gestion.daos;

import com.nidara.sabanasblancas.gestion.controllers.dtos.StockIncrement;
import com.nidara.sabanasblancas.gestion.daos.querybuilders.ProductCombinationQueryBuilder;
import com.nidara.sabanasblancas.gestion.daos.querybuilders.ProductPackQueryBuilder;
import com.nidara.sabanasblancas.gestion.daos.querybuilders.UpdateQueryBuilder;
import com.nidara.sabanasblancas.gestion.daos.rowmappers.ProductRowMapper;
import com.nidara.sabanasblancas.gestion.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Product> getStockProducts(List<Integer> stockIds) {
        if (stockIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<Product> productCombinations = getProductCombinationsByStockId(stockIds);

        List<Product> productPacks = getProductPacksByStockId(stockIds);
        productCombinations.addAll(productPacks);

        return productCombinations;
    }


    private List<Product> getProductCombinationsByStockId(List<Integer> stockIds) {
        String sql = new ProductCombinationQueryBuilder()
                .withColorAndSize()
                .withStock()
                .withStockIdIn(stockIds)
                .build();

        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    private List<Product> getProductPacksByStockId(List<Integer> stockIds) {
        String sql = new ProductPackQueryBuilder()
                .withStock()
                .withStockIdIn(stockIds)
                .build();

        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Transactional
    public void updateProductStocks(List<StockIncrement> stockIncrements) {
        List<Integer> stockIds = stockIncrements.stream().map(StockIncrement::getStockId).collect(Collectors.toList());
        List<Product> products = getStockProducts(stockIds);
        Map<Integer, Integer> quantities = products.stream().collect(Collectors.toMap(Product::getIdStock, Product::getCurrentStock));

        for (StockIncrement increment : stockIncrements) {
            int stockId = increment.getStockId();
            int initialQuantity = quantities.get(stockId);
            int finalQuantity = initialQuantity + increment.getIncrement();
            updateProductStock(stockId, finalQuantity);
//            createStockMovement();
        }
    }

    private void updateProductStock(int stockId, int finalQuantity) {
        UpdateQueryBuilder queryBuilder = new UpdateQueryBuilder("ps_stock_available");
        queryBuilder.setField("quantity", finalQuantity);
        queryBuilder.whereIdField("id_stock_available", stockId);
        jdbcTemplate.update(queryBuilder.build());
    }

}
