package com.aplication.springboot.spring.restfullapi.repositories;

import com.aplication.springboot.spring.restfullapi.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
