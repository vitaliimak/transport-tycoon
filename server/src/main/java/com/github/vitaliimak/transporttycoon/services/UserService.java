package com.github.vitaliimak.transporttycoon.services;

import com.github.vitaliimak.transporttycoon.models.AppUser;
import com.github.vitaliimak.transporttycoon.models.UserDto;
import com.github.vitaliimak.transporttycoon.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void createNewUser(UserDto userDto) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        AppUser user = AppUser.fromDtoToEntity(userDto, passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
    }
}
