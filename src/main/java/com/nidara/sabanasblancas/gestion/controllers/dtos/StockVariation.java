package com.nidara.sabanasblancas.gestion.controllers.dtos;

import java.util.List;

public class StockVariation {

    private String customReason;
    private List<StockIncrement> stockIncrements;

    public String getCustomReason() {
        return customReason;
    }

    public void setCustomReason(String customReason) {
        this.customReason = customReason;
    }

    public List<StockIncrement> getStockIncrements() {
        return stockIncrements;
    }

    public void setStockIncrements(List<StockIncrement> stockIncrements) {
        this.stockIncrements = stockIncrements;
    }
}
