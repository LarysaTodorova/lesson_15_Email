package com.example.lesson_15_email;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class UserController {

    private final UserService service;

    @GetMapping("/registration")
    public ResponseEntity<String> registration(@RequestParam String email) {
        service.registerUser(email);
        return ResponseEntity.ok("Registration initiated. Please check your email");
    }

    @GetMapping("/confirmation")
    public ResponseEntity<String> confirmation(@RequestParam String code) {
        boolean confirmationResult = service.receiveConfirmation(code);

        if (confirmationResult) {
            return ResponseEntity.ok("Confirmation ok");
        } else {
            return ResponseEntity.ok("Invalid confirmation code");
        }
    }

}
