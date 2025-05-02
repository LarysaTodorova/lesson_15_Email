package com.example.lesson_15_email;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByConfirmationCode(String confirmationCode);

}
