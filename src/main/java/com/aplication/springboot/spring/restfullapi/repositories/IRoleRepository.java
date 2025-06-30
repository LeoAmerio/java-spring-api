package com.aplication.springboot.spring.restfullapi.repositories;

import com.aplication.springboot.spring.restfullapi.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface IRoleRepository extends CrudRepository<Role, Long> {

}
