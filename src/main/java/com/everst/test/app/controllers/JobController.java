package com.everst.test.app.controllers;

import com.everst.test.app.models.Job;
import com.everst.test.app.models.Profiles;
import com.everst.test.app.services.JobService;
import com.everst.test.app.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

@RestController
public class JobController {

    private final JobService jobService;
    private final ProfileService profileService;

    public JobController(JobService jobService, ProfileService profileService) {
        this.jobService = jobService;
        this.profileService = profileService;
    }

    @GetMapping("/jobs/unpaid")
    public ResponseEntity<?> getAllUnpaid(@RequestHeader("profile_id") long profileId) {
        Profiles profile = getProfile(profileId);
        if (profile != null) {
            List<Job> jobsUnpaid = jobService.getJobsUnpaid(profile);
            return status(200).body(jobsUnpaid);
        } else {
            return status(NOT_FOUND).body(singletonMap("message", "Profile does not exist"));
        }
    }

    @PostMapping("/jobs-pay/{id}/pay")
    public ResponseEntity<?> payForJob(@PathVariable long id, @RequestHeader("profile_id") long profileId) {
        Profiles profile = getProfile(profileId);
        if (profile != null) {
            try {
                Map<String, String> jobPayed = jobService.payJob(id, profile);
                return ResponseEntity.ok(jobPayed);
            } catch (Exception e) {
                return status(BAD_REQUEST).body(singletonMap("message", e.getMessage()));
            }
        } else {
            return status(NOT_FOUND).body(singletonMap("message", "Profile does not exist"));
        }

    }

    private Profiles getProfile(long id) {
        return profileService.getProfile(id);
    }

}
