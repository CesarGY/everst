package com.everst.test.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "paid")
    private Boolean paid;

    @Column(name = "paymentDate")
    private LocalDateTime paymentDate;

    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ContractId", referencedColumnName = "id", nullable = false)
    private Contract contract;*/

    @Column(name = "ContractId")
    private long contractId;

    public Job() {

    }

    public Job(Long id, String description, double price, boolean paid, LocalDateTime paymentDate, long contractId) {
        this.setId(id);
        this.description = description;
        this.price = price;
        this.paid = paid;
        this.paymentDate = paymentDate;
        this.contractId = contractId;
    }

    public Job(String description, double price, boolean paid, LocalDateTime paymentDate, long contractId) {
        this.description = description;
        this.price = price;
        this.paid = paid;
        this.paymentDate = paymentDate;
        this.contractId = contractId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }
}
