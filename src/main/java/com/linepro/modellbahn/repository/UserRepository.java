package com.linepro.modellbahn.repository;

import static com.linepro.modellbahn.ModellbahnApplication.PREFIX;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.linepro.modellbahn.entity.User;

@Repository(PREFIX + "UserRepository")
public interface UserRepository  extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmationToken(String confirmationToken);

    Optional<User> findByResetToken(String resetToken);
}
