package com.nidara.sabanasblancas.gestion.daos.querybuilders;

import org.junit.Assert;
import org.junit.Test;

public class QuoteQueryBuilderTests {

    @Test
    public void quote_queries_se_construyen_correctamente() {
        String sql = new QuoteQueryBuilder()
                .page(0, 10)
                .build();

        System.out.println(sql);

        String sqlUpper = sql.toUpperCase();

        Assert.assertTrue(sqlUpper.contains("SELECT "));
        Assert.assertFalse(sql.contains(" COUNT(*) "));
        Assert.assertTrue(sqlUpper.contains(" FROM "));
        Assert.assertTrue(sqlUpper.contains(" JOIN "));
        Assert.assertTrue(sqlUpper.contains(" LIMIT "));
        Assert.assertTrue(sqlUpper.contains(" OFFSET "));
        Assert.assertTrue(sqlUpper.contains(" ORDER BY "));
    }

    @Test
    public void quote_queries_count_se_construyen_correctamente() {
        String sql = new QuoteQueryBuilder()
                .buildCount();

        System.out.println(sql);

        String sqlUpper = sql.toUpperCase();

        Assert.assertTrue(sql.contains("SELECT "));
        Assert.assertTrue(sql.contains(" COUNT(*) "));
        Assert.assertTrue(sql.contains(" FROM "));
        Assert.assertTrue(sql.contains(" JOIN "));
        Assert.assertFalse(sql.contains(" LIMIT "));
        Assert.assertFalse(sql.contains(" OFFSET "));
        Assert.assertFalse(sql.contains(" ORDER BY "));
    }

}
