package com.everst.test.runner;

import com.everst.test.app.models.Contract;
import com.everst.test.app.repository.ContractRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
@Component
@Order(2)
@Profile("construction")
public class ContractRunner implements CommandLineRunner {

    private final ContractRepository contractRepository;

    public ContractRunner(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Contract contract1 = new Contract(1L, "bla bla bla", "terminated", 5L, 1L);
        Contract contract2 = new Contract(2L, "bla bla bla", "in_progress", 6L, 1L);
        Contract contract3 = new Contract(3L, "bla bla bla", "in_progress", 6L, 2L);
        Contract contract4 = new Contract(4L, "bla bla bla", "in_progress", 7L, 2L);
        Contract contract5 = new Contract(5L, "bla bla bla", "new", 8L, 3L);
        Contract contract6 = new Contract(6L, "bla bla bla", "in_progress", 7L, 3L);
        Contract contract7 = new Contract(7L, "bla bla bla", "in_progress", 7L, 4L);
        Contract contract8 = new Contract(8L, "bla bla bla", "in_progress", 6L, 4L);
        Contract contract9 = new Contract(9L, "bla bla bla", "in_progress", 8L, 4L);
        /*this.contractRepository.saveAll(
                asList(contract1, contract2, contract3, contract4, contract5, contract6, contract7, contract8, contract9)
        );*/
    }
}
