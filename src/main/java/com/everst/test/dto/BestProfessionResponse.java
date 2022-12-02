package com.everst.test.dto;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */

public class BestProfessionResponse {

    private long id;
    private String fullName;
    private double paid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }
}
