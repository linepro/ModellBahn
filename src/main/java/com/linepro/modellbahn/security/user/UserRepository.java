package com.linepro.modellbahn.security.user;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(PREFIX + "UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmationToken(UUID confirmationToken);

    Optional<User> findByResetToken(UUID resetToken);
}
