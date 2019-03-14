package com.nidara.sabanasblancas.gestion.controllers;

import com.nidara.sabanasblancas.gestion.controllers.dtos.StockIncrement;
import com.nidara.sabanasblancas.gestion.daos.ProductDao;
import com.nidara.sabanasblancas.gestion.exceptions.StockIncrementCannotBeZeroException;
import com.nidara.sabanasblancas.gestion.model.Product;
import com.nidara.sabanasblancas.gestion.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductDao productDao;

    @Autowired
    StockService stockService;

    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    @RequestMapping("/products/{categoryId}")
    public List<Product> getCategoryProducts(@PathVariable("categoryId") int categoryId) {
        return productDao.getByCategory(categoryId);
    }

    @PostMapping("/products/stock")
    public List<Product> getStockProducts(@RequestBody List<Integer> stockIds) {
        return stockService.getStockProducts(stockIds);
    }

    @PutMapping("/products/stock")
    public void updateStockIncrements(@RequestBody List<StockIncrement> stockIncrements) {
        checkIncrementsNonZero(stockIncrements);
        stockService.updateProductStocks(stockIncrements);
    }

    private void checkIncrementsNonZero(List<StockIncrement> stockIncrements) {
        for (StockIncrement increment: stockIncrements) {
            if (increment.getIncrement() == 0) {
                throw new StockIncrementCannotBeZeroException();
            }
        }
    }

}
