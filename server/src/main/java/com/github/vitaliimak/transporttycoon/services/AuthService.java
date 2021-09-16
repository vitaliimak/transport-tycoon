package com.github.vitaliimak.transporttycoon.services;

import com.github.vitaliimak.transporttycoon.models.AppUser;
import com.github.vitaliimak.transporttycoon.repositories.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
@Transactional
public class AuthService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> appUser = userRepository.findByEmail(email);
        if (appUser.isPresent()) {
            return User.builder()
                .username(appUser.get().getEmail())
                .password(appUser.get().getPassword())
                .authorities(emptyList())
                .build();
        }
        throw new UsernameNotFoundException("User with " + email + " does not exist");
    }
}
