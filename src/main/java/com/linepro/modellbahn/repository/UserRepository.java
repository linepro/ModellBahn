package com.linepro.modellbahn.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.User;

@Repository("userRepository")
public interface UserRepository  extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByConfirmationToken(String confirmationToken);
    Optional<User> findByResetToken(String resetToken);
}
