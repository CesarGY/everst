package com.everst.test.app.services;

import com.everst.test.app.models.Contract;
import com.everst.test.app.models.Profiles;
import com.everst.test.app.repository.ContractRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract findById(long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contract with id " + id + " does not exist"));
    }

    @Override
    public Contract getContract(long id, Profiles profile) {
        if (profile.getType().equals("contractor")) {
            return contractRepository.findByIdAndContractorId(id, profile.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Contractor with id " + id + " does not exist"));
        } else {
            return contractRepository.findByIdAndClientId(id, profile.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Client with id " + id + " does not exist"));
        }
    }

    @Override
    public List<Contract> getContracts(Profiles profile) {
        if (profile.getType().equals("contractor")) {
            return contractRepository.findByContractorIdAndStatusNotIn(profile.getId(), Arrays.asList("terminated"));
        } else {
            return contractRepository.findByClientIdAndStatusNotIn(profile.getId(), Arrays.asList("terminated"));
        }
    }

    @Override
    public List<Contract> findContractByClientIdAndStatus(long clientId, String status) {
        return contractRepository.findByClientIdAndStatus(clientId, status);
    }

    @Override
    public List<Contract> findContractByContractorIdAndStatus(long contractorId, String status) {
        return contractRepository.findByContractorIdAndStatus(contractorId, status);
    }
}
