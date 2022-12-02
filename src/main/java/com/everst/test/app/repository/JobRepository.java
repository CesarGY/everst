package com.everst.test.app.repository;

import com.everst.test.app.models.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

    List<Job> findByContractIdInAndPaidIs(List<Long> contractsId, boolean paid);

    List<Job> findByContractIdIn(List<Long> ids);

}
