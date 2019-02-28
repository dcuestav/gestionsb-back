package com.nidara.sabanasblancas.gestion.model;

import com.nidara.sabanasblancas.gestion.exceptions.InvalidProductCategoryIdException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public enum ProductCategory {

    ROOT(2, null),

    BASIC(32, ROOT),
    PREMIUM(33, ROOT),

    COLCHAS(13, ROOT),
    MANTAS_Y_EDREDONES(15, ROOT),
    PROTECTORES(16, ROOT),
    ALMOHADAS(17, ROOT),
    TOALLAS(18, ROOT),
    CUNA(19, ROOT),
    MESA(35, ROOT),

    BASIC_5050(12, BASIC),
    BASIC_100(28, BASIC),
    PERCAL_5050(31, PREMIUM),
    PERCAL_100(29, PREMIUM),
    SATEN(34, PREMIUM),

    JUEGO_SABANAS_BASIC_5050(36, BASIC_5050),
    JUEGO_NORDICO_BASIC_5050(37, BASIC_5050),
    JUEGO_SABANAS_BASIC_100(38, BASIC_100),
    JUEGO_SABANAS_PERCAL_5050(39, PERCAL_5050),
    JUEGO_NORDICO_PERCAL_5050(40, PERCAL_5050),
    JUEGO_SABANAS_PERCAL_100(41, PERCAL_100),
    JUEGO_NORDICO_PERCAL_100(42, PERCAL_100);

    private int id;
    private ProductCategory parent;

    ProductCategory(int id, ProductCategory parent) {
        this.id = id;
        this.parent = parent;
    }

    public static ProductCategory getById(int id) throws InvalidProductCategoryIdException {
        for (ProductCategory productCategory : ProductCategory.values()) {
            if (productCategory.getId()==id) {
                return productCategory;
            }
        }
        throw new InvalidProductCategoryIdException();
    }

    public List<ProductCategory> getThisAndDescendants() {

        List<ProductCategory> productCategories = new LinkedList();
        productCategories.add(this);

        Queue<ProductCategory> parentsToCheck = new LinkedList(productCategories);

        while (!parentsToCheck.isEmpty()) {

            ProductCategory parentToCheck = parentsToCheck.remove();

            for (ProductCategory productCategory : ProductCategory.values()) {
                if (productCategory.parent == parentToCheck) {
                    parentsToCheck.add(productCategory);
                    productCategories.add(productCategory);
                }
            }
        }

        return productCategories;
    }

    public int getId() {
        return id;
    }

}
