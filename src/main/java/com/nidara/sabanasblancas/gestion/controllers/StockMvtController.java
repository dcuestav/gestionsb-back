package com.nidara.sabanasblancas.gestion.controllers;

import com.nidara.sabanasblancas.gestion.model.StockMovement;
import com.nidara.sabanasblancas.gestion.model.dtos.PagedResult;
import com.nidara.sabanasblancas.gestion.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StockMvtController {

    @Autowired
    StockService stockService;

    @RequestMapping("/stockmovements/date/{date}")
    public List<StockMovement> getMovementsByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return stockService.getMovementsByDate(date);
    }

    @RequestMapping("/stockmovements/product/{stockId}")
    public PagedResult<StockMovement> getMovementsByProduct(
            @PathVariable("stockId") int stockId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return stockService.getMovementsByProduct(stockId, page, size);
    }
}
