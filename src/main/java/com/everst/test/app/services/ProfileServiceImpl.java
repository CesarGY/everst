package com.everst.test.app.services;

import com.everst.test.app.dao.ProfileDAO;
import com.everst.test.app.models.Contract;
import com.everst.test.app.models.Job;
import com.everst.test.app.models.Profiles;
import com.everst.test.app.repository.JobRepository;
import com.everst.test.app.repository.ProfileRepository;
import com.everst.test.dto.BalanceRequest;
import com.everst.test.dto.BestProfessionResponse;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static java.util.stream.Collectors.toList;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileDAO profileDAO;
    private final ContractService contractService;

    private final JobRepository jobRepository;
    // private final JobService jobService;

    public ProfileServiceImpl(ProfileRepository profileRepository, ContractService contractService,
                              JobRepository jobRepository, ProfileDAO profileDAO) {
        this.profileRepository = profileRepository;
        this.contractService = contractService;
        this.jobRepository = jobRepository;
        this.profileDAO = profileDAO;
    }

    @Override
    public Profiles getProfile(Long id) {
        return this.profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile with id " + id + " does not exist"));
    }

    @Override
    public Profiles update(Profiles profiles) {
        return profileRepository.save(profiles);
    }

    @Override
    public Map<String, String> depositMoneyClient(Profiles profile, BalanceRequest balanceRequest) throws Exception {
        List<Contract> contracts = contractService.findContractByClientIdAndStatus(profile.getId(), "in_progress");
        List<Long> contractsId = contracts.stream().map(contract -> contract.getId()).collect(toList());
        List<Job> jobs = jobRepository.findByContractIdIn(contractsId);
        double sum = jobs.stream().map(Job::getPrice).mapToDouble(Double::doubleValue).sum();
        double totalToPay = (sum * 0.25);
        if (balanceRequest.getBalance() > totalToPay) {
            throw new Exception("The deposit exceeds the total work to pay");
        } else {
            double totalBalance = profile.getBalance() + balanceRequest.getBalance();
            profile.setBalance(totalBalance);
            profileRepository.save(profile);
            return singletonMap("message", "Balance updated");
        }
    }

    @Override
    public List<BestProfessionResponse> bestClients() {
        return profileDAO.professionWithHigherPayments().stream().sorted((i, j) -> (int) (j.getPaid() - i.getPaid())).collect(toList());
    }
}
