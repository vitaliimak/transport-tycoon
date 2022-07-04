package com.github.vitaliimak.transporttycoon.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
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

    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public static AppUser fromDtoToEntity(UserDto userDto, String encodedPassword) {
        return AppUser.builder()
            .email(userDto.getEmail())
            .password(encodedPassword)
            .lastName(userDto.getLastName())
            .firstName(userDto.getFirstName())
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
