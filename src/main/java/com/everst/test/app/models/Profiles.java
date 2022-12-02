package com.everst.test.app.models;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
@Entity
@Table(name = "Profiles")
public class Profiles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "profession")
    private String profession;

    @Column(name = "balance")
    private double balance;

    @Column(name = "type")
    private String type;

    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;


    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    /*@OneToMany
    @JoinColumn(name = "client_id")
    private List<Contract> contractsClient = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "contractor_id")
    private List<Contract> contractsContractor = new ArrayList<>();*/

    public Profiles() {

    }

    public Profiles(Long id, String name, String lastName, String profession, Double balance, String type) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.profession = profession;
        this.balance = balance;
        this.type = type;
    }

    public Profiles(String name, String lastName, String profession, Double balance, String type) {
        this.name = name;
        this.lastName = lastName;
        this.profession = profession;
        this.balance = balance;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /*public List<Contract> getContractsClient() {
        return contractsClient;
    }

    public void setContractsClient(List<Contract> contractsClient) {
        this.contractsClient = contractsClient;
    }

    public List<Contract> getContractsContractor() {
        return contractsContractor;
    }

    public void setContractsContractor(List<Contract> contractsContractor) {
        this.contractsContractor = contractsContractor;
    }*/
}
