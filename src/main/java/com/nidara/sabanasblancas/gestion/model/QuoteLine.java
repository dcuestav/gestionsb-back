package com.nidara.sabanasblancas.gestion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "gsb_quote_line")
public class QuoteLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quote_line")
    private Integer id;

    @Column
    private Integer lineNumber;

    @Column
    private String productReference;

    @Column
    private String productName;

    @Column
    private String productColor;

    @Column
    private String productSize;

    @Column
    private String productComments;

    @Column
    private BigDecimal productPrice;

    @Column
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_quote")
    @JsonIgnore
    private Quote quote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getProductReference() {
        return productReference;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductComments() {
        return productComments;
    }

    public void setProductComments(String productComments) {
        this.productComments = productComments;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public BigDecimal getTotal() {
        return productPrice.multiply(new BigDecimal(quantity));
    }
}
