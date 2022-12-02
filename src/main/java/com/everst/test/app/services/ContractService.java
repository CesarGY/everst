package com.everst.test.app.services;

import com.everst.test.app.models.Contract;
import com.everst.test.app.models.Profiles;

import java.util.List;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
public interface ContractService {

    Contract findById(long id);

    Contract getContract(long id, Profiles profile);

    List<Contract> getContracts(Profiles profile);

    List<Contract> findContractByClientIdAndStatus(long clientId, String status);

    List<Contract> findContractByContractorIdAndStatus(long contractorId, String status);

}
