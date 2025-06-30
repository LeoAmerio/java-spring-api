package com.aplication.springboot.spring.restfullapi.repositories;

import com.aplication.springboot.spring.restfullapi.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {

}
