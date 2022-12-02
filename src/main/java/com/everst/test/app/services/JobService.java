package com.everst.test.app.services;

import com.everst.test.app.models.Job;
import com.everst.test.app.models.Profiles;

import java.util.List;
import java.util.Map;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
public interface JobService {

    List<Job> getJobsUnpaid(Profiles profile);

    Map<String, String> payJob(long jobId, Profiles profile) throws Exception;

    List<Job> findJobsByContractsId(List<Long> contractsId);

}
