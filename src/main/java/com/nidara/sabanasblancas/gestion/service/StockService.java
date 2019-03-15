package com.nidara.sabanasblancas.gestion.service;

import com.nidara.sabanasblancas.gestion.controllers.dtos.StockIncrement;
import com.nidara.sabanasblancas.gestion.controllers.dtos.StockVariation;
import com.nidara.sabanasblancas.gestion.daos.ProductDao;
import com.nidara.sabanasblancas.gestion.daos.StockMvtReasonDao;
import com.nidara.sabanasblancas.gestion.daos.StockMvtRepository;
import com.nidara.sabanasblancas.gestion.model.Product;
import com.nidara.sabanasblancas.gestion.model.StockMovement;
import com.nidara.sabanasblancas.gestion.model.enums.StockMvtReasonEnum;
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

    @Autowired
    StockMvtReasonDao stockMvtReasonDao;

    @Transactional
    public void updateProductStocks(StockVariation stockVariation) {
        String reason = stockVariation.getCustomReason();
        List<StockMovement> stockMovements;
        if (reason == null || reason.isEmpty()) {
            stockMovements = createMovementsWithDefaultReason(stockVariation.getStockIncrements());
        } else {
            int reasonId = stockMvtReasonDao.createReason(stockVariation.getCustomReason());
            stockMovements = createMovementsWithCustomReason(stockVariation.getStockIncrements(), reasonId);
        }
        stockMvtRepository.saveAll(stockMovements);
        productDao.updateProductStocks(stockVariation.getStockIncrements());
    }

    public List<Product> getStockProducts(List<Integer> stockIds) {
        return productDao.getStockProducts(stockIds);
    }

    private List<StockMovement> createMovementsWithCustomReason(List<StockIncrement> stockIncrements, int reasonId) {
        return stockIncrements.stream().map( increment -> new StockMovement(increment, reasonId)).collect(Collectors.toList());
    }

    private List<StockMovement> createMovementsWithDefaultReason(List<StockIncrement> stockIncrements) {
        return stockIncrements.stream().map( increment -> {
            int reasonId = StockMvtReasonEnum.INCREASE_DUE_TO_EMPLOYEE_EDITION.getValue();
            if (increment.getIncrement() < 0) {
                reasonId = StockMvtReasonEnum.DECREASE_DUE_TO_EMPLOYEE_EDITION.getValue();
            }
            return new StockMovement(increment, reasonId);
        }).collect(Collectors.toList());
    }
}
