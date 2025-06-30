package com.aplication.springboot.spring.restfullapi.repositories;

import com.aplication.springboot.spring.restfullapi.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
