package com.club.repository;

import com.club.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String username);

    Optional<User> findByEmail(String email);

    User getUserByEmail(String email);

}
