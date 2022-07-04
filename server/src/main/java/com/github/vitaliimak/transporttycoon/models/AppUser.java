package com.github.vitaliimak.transporttycoon.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Entity
@Data
@Builder
@Table(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    private static final int ACTIVATION_KEY_LENGTH = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    private String password;

    private String firstName;

    private String lastName;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Size(max = ACTIVATION_KEY_LENGTH)
    @Column(name = "activation_key", length = ACTIVATION_KEY_LENGTH)
    @JsonIgnore
    private String activationKey;

    public static AppUser fromDtoToEntity(UserDto userDto, String encodedPassword) {
        return AppUser.builder()
            .email(userDto.getEmail())
            .password(encodedPassword)
            .lastName(userDto.getLastName())
            .firstName(userDto.getFirstName())
            .activationKey(RandomString.make(ACTIVATION_KEY_LENGTH))
            .build();
    }

    public static UserDto fromEntityToDto(AppUser appUser) {
        return UserDto.builder()
            .email(appUser.getEmail())
            .lastName(appUser.getLastName())
            .firstName(appUser.getFirstName())
            .build();
    }
}
