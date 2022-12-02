package com.everst.test.app.repository;

import com.everst.test.app.models.Profiles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfileRepository extends CrudRepository<Profiles, Long> {
}
