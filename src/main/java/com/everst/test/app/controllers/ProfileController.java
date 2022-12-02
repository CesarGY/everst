package com.everst.test.app.controllers;

import com.everst.test.app.models.Profiles;
import com.everst.test.app.services.ProfileService;
import com.everst.test.dto.BalanceRequest;
import com.everst.test.dto.BestProfessionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
@RestController
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/balances/deposit/{userId}")
    public ResponseEntity<?> depositMoneyClient(@PathVariable long userId, @RequestBody BalanceRequest balanceRequest) {
        Profiles profile = getProfile(userId);
        if (profile != null) {
            try {
                Map<String, String> stringStringMap = profileService.depositMoneyClient(profile, balanceRequest);
                return ok(stringStringMap);
            } catch (Exception e) {
                return status(NOT_FOUND).body(singletonMap("message", e.getMessage()));
            }
        } else {
            return status(NOT_FOUND).body(singletonMap("message", "Profile does not exist"));
        }
    }


    @GetMapping("/admin/best-clients")
    public ResponseEntity bestClients() {
        try {
            List<BestProfessionResponse> bestProfessions = profileService.bestClients();
            return ok(bestProfessions);
        } catch (Exception e) {
            return status(INTERNAL_SERVER_ERROR).body(singletonMap("message", "Error to retrieve information " + e.getMessage()));
        }

    }


    private Profiles getProfile(long id) {
        return profileService.getProfile(id);
    }
}
