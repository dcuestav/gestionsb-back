package com.nidara.sabanasblancas.gestion.daos.querybuilders;

import org.junit.Assert;
import org.junit.Test;

public class QuoteQueryBuilderTests {

    @Test
    public void quote_queries_se_construyen_correctamente() {
        String sql = new QuoteQueryBuilder().page(0, 10).build();

        System.out.println(sql);

        Assert.assertTrue(sql.contains("SELECT "));
        Assert.assertTrue(sql.contains(" FROM "));
        Assert.assertTrue(sql.contains(" LIMIT "));
        Assert.assertTrue(sql.contains(" OFFSET "));
        Assert.assertTrue(sql.contains(" ORDER BY "));
    }

}
