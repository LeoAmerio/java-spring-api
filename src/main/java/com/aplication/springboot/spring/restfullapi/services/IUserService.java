package com.aplication.springboot.spring.restfullapi.services;

import com.aplication.springboot.spring.restfullapi.entities.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User save(User user);
    boolean existsByUsername(String username);
}
