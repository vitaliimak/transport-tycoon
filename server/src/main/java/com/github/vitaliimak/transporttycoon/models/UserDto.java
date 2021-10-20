package com.github.vitaliimak.transporttycoon.models;

import com.github.vitaliimak.transporttycoon.annotations.UniqueEmail;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    @NotBlank
    @Email
    @UniqueEmail
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    private String password;

    private String firstName;

    private String lastName;

    private List<Role> roles;
}
