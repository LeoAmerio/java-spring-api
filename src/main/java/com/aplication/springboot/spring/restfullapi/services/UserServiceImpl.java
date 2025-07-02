package com.aplication.springboot.spring.restfullapi.services;

import com.aplication.springboot.spring.restfullapi.entities.Role;
import com.aplication.springboot.spring.restfullapi.entities.User;
import com.aplication.springboot.spring.restfullapi.repositories.IRoleRepository;
import com.aplication.springboot.spring.restfullapi.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {

        Optional<Role> roleUserOpt = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        roleUserOpt.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Role> optRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
