package com.everst.test.app.repository;

import com.everst.test.app.models.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {

    Optional<Contract> findByIdAndClientId(long id, long clientId);

    Optional<Contract> findByIdAndContractorId(long id, long contractorId);

    List<Contract> findByClientIdAndStatusNotIn(long clientId, List<String> status);

    List<Contract> findByContractorIdAndStatusNotIn(long clientId, List<String> status);

    List<Contract> findByClientIdAndStatus(long clientId, String status);

    List<Contract> findByContractorIdAndStatus(long contractorId, String status);

}
