package com.linepro.modellbahn.security.user;

import com.linepro.modellbahn.configuration.UserMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends UserMessage {
    private UserModel user;
}
