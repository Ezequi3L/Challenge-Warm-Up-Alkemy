package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.UserEntity;
import alkemy.challenge.Challenge.Alkemy.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserEntityService userEntityService;

    @PostMapping("/auth/sign_up")
    public ResponseEntity<UserEntity> newUser(@RequestBody UserEntity newUser){
            return ResponseEntity.status(HttpStatus.CREATED).body(userEntityService.save(newUser));
    }
}
