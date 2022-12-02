package com.everst.test.app.services;

import com.everst.test.app.models.Contract;
import com.everst.test.app.models.Job;
import com.everst.test.app.models.Profiles;
import com.everst.test.app.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final ContractService contractService;
    private final ProfileService profileService;

    public JobServiceImpl(JobRepository jobRepository, ContractService contractService, ProfileService profileService) {
        this.jobRepository = jobRepository;
        this.contractService = contractService;
        this.profileService = profileService;
    }


    @Override
    public List<Job> getJobsUnpaid(Profiles profile) {
        List<Contract> contracts = null;
        if (profile.getType().equals("contractor")) {
            contracts = contractService.findContractByContractorIdAndStatus(profile.getId(), "in_progress");
        } else {
            contracts = contractService.findContractByClientIdAndStatus(profile.getId(), "in_progress");
        }
        List<Long> contractsId = contracts.stream().map(contract -> contract.getId()).collect(Collectors.toList());

        return jobRepository.findByContractIdInAndPaidIs(contractsId, false);
    }

    @Transactional
    @Override
    public Map<String, String> payJob(long jobId, Profiles profile) throws Exception {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("Job with id " + jobId + " does not exist"));

        if (job.isPaid()) {
            return Collections.singletonMap("message", "This job has been paid");
        } else {
            if (profile.getBalance() >= job.getPrice()) {
                Contract contract = contractService.findById(job.getContractId());
                Profiles contractor = profileService.getProfile(contract.getContractorId());

                double customerBalance = profile.getBalance() - job.getPrice();
                double contractorBalance = contractor.getBalance() + job.getPrice();

                // update balance client
                profile.setBalance(customerBalance);
                profileService.update(profile);

                // update balance contractor
                contractor.setBalance(contractorBalance);
                profileService.update(contractor);

                // update job, payment confirmation
                job.setPaid(true);
                job.setPaymentDate(LocalDateTime.now());
                jobRepository.save(job);

            } else {
                throw new Exception("Error to process pay because the customer does not have enough balance");
            }
            return Collections.singletonMap("message", "Payment confirmed");
        }
    }

    @Override
    public List<Job> findJobsByContractsId(List<Long> contractsId) {
        return jobRepository.findByContractIdIn(contractsId);
    }
}
