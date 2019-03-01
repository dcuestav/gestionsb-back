package com.nidara.sabanasblancas.gestion.model;

import java.math.BigDecimal;
import java.util.Date;

public class Quote {
    private Integer id;
    private Date date;
    private String comments;
    private String deliveryTime;
    private Taxes taxes;
    private QuoteState state;
    private BigDecimal total;

    private QuoteClient client;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Taxes getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    public QuoteState getState() {
        return state;
    }

    public void setState(QuoteState state) {
        this.state = state;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public QuoteClient getClient() {
        return client;
    }

    public void setClient(QuoteClient client) {
        this.client = client;
    }
}
