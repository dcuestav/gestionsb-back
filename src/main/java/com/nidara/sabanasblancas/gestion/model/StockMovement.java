package com.nidara.sabanasblancas.gestion.model;

import com.nidara.sabanasblancas.gestion.controllers.dtos.StockIncrement;
import com.nidara.sabanasblancas.gestion.model.enums.StockMvtReason;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ps_stock_mvt")
public class StockMovement {

    static final int DEFAULT_EMPLOYEE = 1;
    static final String DEFAULT_FIRSTNAME = "GestionSB";
    static final String DEFAULT_LASTNAME = "";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock_mvt")
    private Long id;

    @Column(name = "id_stock")
    private Integer idStock;

    @Column(name = "id_order")
    private Integer idOrder;

    @Column(name = "id_stock_mvt_reason")
    @Enumerated(EnumType.ORDINAL)
    private StockMvtReason reason;

    @Column(name = "id_employee")
    private Integer idEmployee;

    @Column(name = "employee_firstname")
    private String employeeFirstName;

    @Column(name = "employee_lastname")
    private String employeeLastName;

    @Column(name = "physical_quantity")
    private Integer quantity;

    @Column(name = "date_add")
    private Date date;

    @Column(name = "sign")
    private Integer sign;

    public StockMovement() {
        this.sign = 1;
        this.idEmployee = DEFAULT_EMPLOYEE;
        this.employeeFirstName = DEFAULT_FIRSTNAME;
        this.employeeLastName = DEFAULT_LASTNAME;
    }

    public StockMovement(Integer idStock, StockMvtReason reason, Integer quantity) {
        this();
        this.idStock = idStock;
        this.reason = reason;
        setQuantity(quantity);
        this.date = new Date();
    }

    public StockMovement(StockIncrement increment) {
        this(increment.getStockId(), StockMvtReason.INCREASE_DUE_TO_EMPLOYEE_EDITION, increment.getIncrement());
        if (increment.getIncrement()<0) {
            this.reason = StockMvtReason.DECREASE_DUE_TO_EMPLOYEE_EDITION;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public StockMvtReason getReason() {
        return reason;
    }

    public void setReason(StockMvtReason reason) {
        this.reason = reason;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public void setQuantity(Integer quantity) {
        if (quantity < 0) {
            this.quantity = -quantity;
            this.sign = -1;
        } else {
            this.quantity = quantity;
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }
}
