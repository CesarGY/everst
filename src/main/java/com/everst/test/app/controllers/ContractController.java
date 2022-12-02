package com.everst.test.app.controllers;

import com.everst.test.app.models.Contract;
import com.everst.test.app.models.Profiles;
import com.everst.test.app.services.ContractService;
import com.everst.test.app.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
@RestController
public class ContractController {

    private final ContractService contractService;
    private final ProfileService profileService;

    public ContractController(ContractService contractService, ProfileService profileService) {
        this.contractService = contractService;
        this.profileService = profileService;
    }

    @GetMapping("/contracts/{id}")
    public ResponseEntity<?> getContractById(@PathVariable long id, @RequestHeader("profile_id") long profileId) {
        Profiles profile = getProfile(profileId);
        if (profile != null) {
            Contract contract = contractService.getContract(id, profile);
            return ResponseEntity.status(200).body(contract);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Profile does not exist"));
        }
    }

    @GetMapping("/contracts")
    public ResponseEntity<?> getContracts(@RequestHeader("profile_id") long profileId) {
        Profiles profile = getProfile(profileId);
        if (profile != null) {
            List<Contract> contracts = contractService.getContracts(profile);
            return ResponseEntity.status(200).body(contracts);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Profile does not exist"));
        }
    }

    private Profiles getProfile(long id) {
        return profileService.getProfile(id);
    }

}
