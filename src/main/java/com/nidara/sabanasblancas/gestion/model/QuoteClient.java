package com.nidara.sabanasblancas.gestion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "gsb_quote_client")
public class QuoteClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quote_customer")
    private Integer id;

    @Column(name = "ps_id_customer")
    @JsonIgnore
    private Integer prestaId;

    @Column
    private String name;

    @Column
    private String address;

    @Column(name = "company_name")
    private String company;

    @Column
    private String email;

    @Column(name = "telephone")
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrestaId() {
        return prestaId;
    }

    public void setPrestaId(Integer prestaId) {
        this.prestaId = prestaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
