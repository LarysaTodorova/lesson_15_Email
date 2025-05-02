package com.example.lesson_15_email;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final EmailService emailService;

    public void registerUser(String email) {
        /*
        нужно сгенерировать UUID код и его отправить
         */
        User newUser = User.builder()
                .email(email)
                .confirmationCode(UUID.randomUUID().toString())
                .isConfirmed(false)
                .build();

        repository.save(newUser);

// отправляем email

        emailService.sendConfirmationCodeByEmail(newUser);

    }

    public boolean receiveConfirmation(String code) {
        User userByCode = repository.findByConfirmationCode(code)
                .orElseThrow(() -> new IllegalArgumentException("User with code " + code + " not found"));

        userByCode.setConfirmed(true);
        repository.save(userByCode);

        return true;
    }

}
