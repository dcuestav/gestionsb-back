package com.nidara.sabanasblancas.gestion.service;

import com.nidara.sabanasblancas.gestion.controllers.dtos.StockIncrement;
import com.nidara.sabanasblancas.gestion.daos.ProductDao;
import com.nidara.sabanasblancas.gestion.daos.StockMvtRepository;
import com.nidara.sabanasblancas.gestion.model.Product;
import com.nidara.sabanasblancas.gestion.model.StockMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    StockMvtRepository stockMvtRepository;

    @Autowired
    ProductDao productDao;

    @Transactional
    public void updateProductStocks(List<StockIncrement> stockIncrements) {
        productDao.updateProductStocks(stockIncrements);
        stockMvtRepository.saveAll(createMovements(stockIncrements));
    }

    public List<Product> getStockProducts(List<Integer> stockIds) {
        return productDao.getStockProducts(stockIds);
    }

    private List<StockMovement> createMovements(List<StockIncrement> stockIncrements) {
        return stockIncrements.stream().map( increment -> new StockMovement(increment)).collect(Collectors.toList());
    }
}
