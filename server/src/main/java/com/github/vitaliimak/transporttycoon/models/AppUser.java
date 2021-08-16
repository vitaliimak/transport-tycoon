package com.github.vitaliimak.transporttycoon.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AppUser {
    @Id
    private Long id;

    private String email;

    private String password;
}
