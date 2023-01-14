package com.example.aiprojekt.security;

import com.example.aiprojekt.models.Role;
import com.example.aiprojekt.models.User;
import com.example.aiprojekt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
class UserInformation implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String principal) throws UsernameNotFoundException {
        User user = getUserByPrinicipal(principal);
        return new org.springframework.security.core.userdetails.User(user.getId(), String.valueOf(user.getPassword()), getAuthorities(user.getRoles()));
    }

    private User getUserByPrinicipal(String principal) {
        return userRepository.findByEmail(principal)
                .orElseGet(() -> userRepository.findByLogin(principal).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    private Collection<SimpleGrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
}