package com.everst.test.runner;

import com.everst.test.app.models.Profiles;
import com.everst.test.app.repository.ProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
@Component
@Order(1)
@Profile("construction")
public class ProfileRunner implements CommandLineRunner {



    private final ProfileRepository profileRepository;

    public ProfileRunner(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Creating profiles");
        Profiles profile1 = new Profiles(1L, "Harry", "Potter", "Wizard", 1250D, "client");
        Profiles profile2 = new Profiles(2L, "Mr", "Robot", "Hacker", 231.11D, "client");
        Profiles profile3 = new Profiles(3L, "John", "Snow", "Knows nothing", 451.3D, "client");
        Profiles profile4 = new Profiles(4L, "Ash", "Kethcum", "Pokemon master", 1.3D, "client");
        Profiles profile5 = new Profiles(5L, "John", "Lenon", "Musician", 64D, "contractor");
        Profiles profile6 = new Profiles(6L, "Linus", "Torvalds", "Programmer", 1214D, "contractor");
        Profiles profile7 = new Profiles(7L, "Alan", "Turing", "Programmer", 22D, "contractor");
        Profiles profile8 = new Profiles(8L, "Aragorn", "II Elessar Telcontarvalds", "Fighter", 314D, "contractor");

        profileRepository.save(profile1);


        // profileRepository.saveAll(Arrays.asList(profile1, profile2, profile3, profile4, profile5, profile6, profile7, profile8));
    }
}



