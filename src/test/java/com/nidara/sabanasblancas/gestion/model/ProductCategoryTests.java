package com.nidara.sabanasblancas.gestion.model;

import com.nidara.sabanasblancas.gestion.model.enums.ProductCategory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ProductCategoryTests {

    @Test
    public void test_product_category_get_descendants_level_0() {

        List<ProductCategory> list = ProductCategory.COLCHAS.getThisAndDescendants();

        Assert.assertTrue(list.contains(ProductCategory.COLCHAS));
        Assert.assertTrue(list.size()==1);
    }

    @Test
    public void test_product_category_get_descendants_level_1() {

        List<ProductCategory> list = ProductCategory.BASIC_5050.getThisAndDescendants();

        Assert.assertTrue(list.contains(ProductCategory.BASIC_5050));
        Assert.assertTrue(list.contains(ProductCategory.JUEGO_SABANAS_BASIC_5050));
        Assert.assertTrue(list.contains(ProductCategory.JUEGO_NORDICO_BASIC_5050));

        Assert.assertTrue(list.size()==3);
    }

    @Test
    public void test_product_category_get_descendants_level_2() {

        List<ProductCategory> list = ProductCategory.BASIC.getThisAndDescendants();

        Assert.assertTrue(list.contains(ProductCategory.BASIC));

        Assert.assertTrue(list.contains(ProductCategory.BASIC_5050));
        Assert.assertTrue(list.contains(ProductCategory.JUEGO_SABANAS_BASIC_5050));
        Assert.assertTrue(list.contains(ProductCategory.JUEGO_NORDICO_BASIC_5050));

        Assert.assertTrue(list.contains(ProductCategory.BASIC_100));
        Assert.assertTrue(list.contains(ProductCategory.JUEGO_SABANAS_BASIC_100));

        Assert.assertTrue(list.size()==6);
    }
}
