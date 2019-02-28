package com.nidara.sabanasblancas.gestion.controllers;

import com.nidara.sabanasblancas.gestion.daos.ProductDao;
import com.nidara.sabanasblancas.gestion.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ProductController {

    @Autowired
    ProductDao productDao;

    @RequestMapping("/product")
    public List<Product> getCategoryProducts(@RequestParam("categoryId") int categoryId) {

        return productDao.getByCategory(categoryId);
    }

}
