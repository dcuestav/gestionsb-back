package com.nidara.sabanasblancas.gestion.controllers;

import com.nidara.sabanasblancas.gestion.daos.ProductDao;
import com.nidara.sabanasblancas.gestion.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductDao productDao;

    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    @RequestMapping("/products/{categoryId}")
    public List<Product> getCategoryProducts(@PathVariable("categoryId") int categoryId) {
        return productDao.getByCategory(categoryId);
    }

}
