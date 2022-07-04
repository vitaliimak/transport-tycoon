package com.github.vitaliimak.transporttycoon.services;

import com.github.vitaliimak.transporttycoon.models.AppUser;
import com.github.vitaliimak.transporttycoon.models.UserDto;
import com.github.vitaliimak.transporttycoon.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;

    public void createNewUser(UserDto userDto) throws MessagingException, UnsupportedEncodingException {
        AppUser user = AppUser.fromDtoToEntity(userDto, passwordEncoder.encode(userDto.getPassword()));
        String siteUrl = "localhost";
        mailService.sendActivationEmail(user, siteUrl);

        userRepository.save(user);
    }

}
