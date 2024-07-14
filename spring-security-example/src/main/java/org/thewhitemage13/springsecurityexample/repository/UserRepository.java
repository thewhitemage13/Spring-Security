package org.thewhitemage13.springsecurityexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thewhitemage13.springsecurityexample.model.MyUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByName(String name);
}
