package com.github.vitaliimak.transporttycoon.annotations;

import com.github.vitaliimak.transporttycoon.annotations.UniqueEmail;
import com.github.vitaliimak.transporttycoon.repositories.UserRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueEmailAnnotationValidator implements ConstraintValidator<UniqueEmail, String> {

    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.findByEmail(email).isPresent();
    }
}
