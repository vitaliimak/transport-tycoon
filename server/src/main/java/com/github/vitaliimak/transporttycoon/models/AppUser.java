package com.github.vitaliimak.transporttycoon.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class AppUser {
    @Id
    private Long id;

    private String email;

    private String password;
}
