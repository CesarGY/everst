package com.everst.test.runner;

import com.everst.test.app.models.Job;
import com.everst.test.app.repository.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
@Component
@Order(3)
@Profile("construction")
public class JobRunner implements CommandLineRunner {

    private final JobRepository jobRepository;

    public JobRunner(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Job job1 = new Job(1L, "work", 200D, false, null, 1);
        Job job2 = new Job(2L, "work", 201D, false, null, 2);
        Job job3 = new Job(3L, "work", 202D, false, null, 3);
        Job job4 = new Job(4L, "work", 200D, false, null, 4);
        Job job5 = new Job(5L, "work", 200D, false, null, 7);
        Job job6 = new Job(6L, "work", 2020D, true, LocalDateTime.now(), 7);
        Job job7 = new Job(7L, "work", 200D, true, LocalDateTime.now(), 2);
        Job job8 = new Job(8L, "work", 200D, true, LocalDateTime.now(), 3);
        Job job9 = new Job(9L, "work", 200D, true, LocalDateTime.now(), 1);
        Job job10 = new Job(10L, "work", 200D, true, LocalDateTime.now(), 5);
        Job job11 = new Job(11L, "work", 21D, true, LocalDateTime.now(), 1);
        Job job12 = new Job(12L, "work", 21D, true, LocalDateTime.now(), 2);
        Job job13 = new Job(13L, "work", 121D, true, LocalDateTime.now(), 3);
        Job job14 = new Job(14L, "work", 121D, true, LocalDateTime.now(), 3);
        /*jobRepository.saveAll(
                asList(job1, job2, job3, job4, job5, job6, job7, job8, job9, job10, job11, job12, job13, job14)
        );*/
    }
}
