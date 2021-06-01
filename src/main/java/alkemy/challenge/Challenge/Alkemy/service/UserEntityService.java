package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.model.UserEntity;
import alkemy.challenge.Challenge.Alkemy.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEntityService {

    private final UserEntityRepository repository;

    private final PasswordEncoder passwordEncoder;


    Optional<UserEntity> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<UserEntity> findById(Long id) {
        return repository.findById(id);
    }

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public UserEntity save(UserEntity userEntity) {
        return repository.save(userEntity);
    }

    public UserEntity newUser (UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        try {
            return save(userEntity);
        } catch (DataIntegrityViolationException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username is already in use");
        }
    }

}
