package com.nidara.sabanasblancas.gestion.model;

import com.nidara.sabanasblancas.gestion.model.enums.QuoteState;
import com.nidara.sabanasblancas.gestion.model.enums.Taxes;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "gsb_quote")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quote")
    private Integer id;

    @Column(name = "quote_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column
    private String comments;

    @Column
    private String deliveryTime;

    @Column
    @Enumerated(EnumType.STRING)
    private Taxes taxes;

    @Column
    @Enumerated(EnumType.STRING)
    private QuoteState state;

    @Column
    private BigDecimal total;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_quote_customer")
    private QuoteClient client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quote", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<QuoteLine> lines;

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

    public List<QuoteLine> getLines() {
        return lines;
    }

    public void setLines(List<QuoteLine> lines) {
        this.lines = lines;
        if (lines!=null) {
            for (QuoteLine line : lines) {
                line.setQuote(this);
            }
        }
    }

    public void recalculateTotal() {
        if (lines!=null) {
            this.total = this.lines.stream().map(QuoteLine::getTotal).reduce(BigDecimal::add).get();
        }
    }
}
