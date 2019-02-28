package com.nidara.sabanasblancas.gestion.daos.querybuilders;

import com.nidara.sabanasblancas.gestion.exceptions.InvalidProductCategoryIdException;
import com.nidara.sabanasblancas.gestion.model.ProductCategory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WithCategoryClauseHelper {

    private static WithCategoryClauseHelper ourInstance = new WithCategoryClauseHelper();

    public static WithCategoryClauseHelper getInstance() {
        return ourInstance;
    }

    private WithCategoryClauseHelper() {
    }

    public String getWhereClauseForCategory(int id) {

        List<Integer> categoryIds = null;
        try {
            ProductCategory productCategory = ProductCategory.getById(id);
            categoryIds = productCategory.getThisAndDescendants().stream().map( item -> item.getId() ).collect(Collectors.toList());

        } catch (InvalidProductCategoryIdException e) {
            categoryIds = Collections.singletonList(id);
        }

        return buildStringForCategories(categoryIds);
    }

    private String buildStringForCategories(List<Integer> categoryIds) {
        StringBuilder builder = new StringBuilder();
        builder.append("p.id_category_default IN(");
        for (Integer categoryId : categoryIds) {
            builder.append(categoryId);
            builder.append(",");
        }
        builder.append("-1) ");
        return builder.toString();
    }


}
