package com.linepro.modellbahn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserModel {

    private String confirmationToken;

    private int id;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private boolean enabled;

    private String resetToken;
}
