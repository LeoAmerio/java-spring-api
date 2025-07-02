package com.aplication.springboot.spring.restfullapi.services;

import com.aplication.springboot.spring.restfullapi.entities.User;
import com.aplication.springboot.spring.restfullapi.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    public JpaUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("No se ha encontrado el usuario con el username: %s", username)
                ));

        return buildUserDetails(user);
    };

    private UserDetails buildUserDetails(User user) {
        List<GrantedAuthority> authorities = getUserAuthorities(user);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                authorities
        );
    }

    private List<GrantedAuthority> getUserAuthorities(User user) {
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

//    @Transactional(readOnly = true)
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userOpt = userRepository.findByUsername(username);
//
//        if (userOpt.isEmpty()) {
//            throw new UsernameNotFoundException(String.format("No se ha encontrado el usuario con el username: %s", username));
//        }
//
//        User user = userOpt.orElseThrow();
//
//        List<GrantedAuthority> authorities = user.getRoles()
//                .stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.isEnabled(),
//                true,
//                true,
//                true,
//        authorities
//        );
//    }
}
