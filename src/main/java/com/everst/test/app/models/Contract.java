package com.everst.test.app.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity {

    @Column(name = "terms")
    private String terms;

    @Column(name = "status")
    private String status;

    //(mappedBy = "contract", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany
    @JoinColumn(name = "ContractId", referencedColumnName = "id")
    private List<Job> jobs = new ArrayList<>();


    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contractor_id", referencedColumnName = "id", nullable = false)
    private Profiles profileContractor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Profiles profileClient;*/

    @Column(name = "ContractorId")
    private long contractorId;

    @Column(name = "ClientId")
    private long clientId;

    public Contract() {


    }

    public Contract(Long id, String terms, String status, long contractorId, long clientId) {
        this.setId(id);
        this.terms = terms;
        this.status = status;
        this.contractorId = contractorId;
        this.clientId = clientId;
    }

    public Contract(String terms, String status, long contractorId, long clientId) {
        this.terms = terms;
        this.status = status;
        this.contractorId = contractorId;
        this.clientId = clientId;
    }


    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public long getContractorId() {
        return contractorId;
    }

    public void setContractorId(long contractorId) {
        this.contractorId = contractorId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
}
