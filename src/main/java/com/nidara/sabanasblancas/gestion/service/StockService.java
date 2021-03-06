package com.nidara.sabanasblancas.gestion.service;

import com.nidara.sabanasblancas.gestion.controllers.dtos.StockIncrement;
import com.nidara.sabanasblancas.gestion.controllers.dtos.StockVariation;
import com.nidara.sabanasblancas.gestion.daos.ProductDao;
import com.nidara.sabanasblancas.gestion.daos.StockMvtReasonDao;
import com.nidara.sabanasblancas.gestion.daos.StockMvtRepository;
import com.nidara.sabanasblancas.gestion.model.Product;
import com.nidara.sabanasblancas.gestion.model.StockMovement;
import com.nidara.sabanasblancas.gestion.model.StockMvtReason;
import com.nidara.sabanasblancas.gestion.model.dtos.PagedResult;
import com.nidara.sabanasblancas.gestion.model.enums.StockMvtReasonEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
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

    @Transactional(readOnly = true)
    public List<StockMovement> getMovementsByDate(LocalDate date) {
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        Date from = Date.from(date.atStartOfDay(zoneId).toInstant());
        Date until = Date.from(date.plusDays(1).atStartOfDay(zoneId).toInstant().minusMillis(1));
        List<StockMovement> movements = stockMvtRepository.getByDateBetween(from, until);
        if (!movements.isEmpty()) {
            fillWithReasons(movements);
            fillWithProducts(movements);
        }
        return movements;
    }

    @Transactional(readOnly = true)
    public PagedResult<StockMovement> getMovementsByProduct(int stockId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<StockMovement> page = stockMvtRepository.getByIdStockOrderByIdDesc(stockId, pageable);
        List<StockMovement> movements = page.getContent();
        if (!movements.isEmpty()) {
            fillWithReasons(movements);
            fillWithProducts(movements);
        }
        return new PagedResult(page);
    }

    private void fillWithProducts(List<StockMovement> movements) {
        List<Integer> stockIds = movements.stream().map(StockMovement::getIdStock).distinct().collect(Collectors.toList());
        List<Product> products = productDao.getStockProducts(stockIds);
        Map<Integer, Product> productMap = products.stream().collect(Collectors.toMap(Product::getIdStock, (product -> product)));
        for (StockMovement movement: movements) {
            movement.setProduct(productMap.get(movement.getIdStock()));
        }
    }

    private void fillWithReasons(List<StockMovement> movements) {
        List<Integer> reasonIds = movements.stream().map(StockMovement::getReasonId).distinct().collect(Collectors.toList());
        List<StockMvtReason> reasons = stockMvtReasonDao.getReasons(reasonIds);
        Map<Integer, String> reasonMap = reasons.stream().collect(Collectors.toMap(StockMvtReason::getId, StockMvtReason::getName));
        for (StockMovement movement: movements) {
            movement.setReason(reasonMap.get(movement.getReasonId()));
        }
    }
}
