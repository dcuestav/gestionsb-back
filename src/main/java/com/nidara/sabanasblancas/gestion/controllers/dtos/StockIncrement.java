package com.nidara.sabanasblancas.gestion.controllers.dtos;

public class StockIncrement {

    private int stockId;
    private int increment;

    public StockIncrement() {
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
