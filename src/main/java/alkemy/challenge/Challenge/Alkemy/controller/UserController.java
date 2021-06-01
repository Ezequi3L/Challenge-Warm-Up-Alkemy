package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.dto.GetUserDto;
import alkemy.challenge.Challenge.Alkemy.dto.UserDtoConverter;
import alkemy.challenge.Challenge.Alkemy.model.UserEntity;
import alkemy.challenge.Challenge.Alkemy.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserEntityService userEntityService;
    private final UserDtoConverter userDtoConverter;

    @PostMapping("/auth/sign_up")
    public GetUserDto newUser(@RequestBody UserEntity newUser){
            return userDtoConverter.convertUserEntityToGetUserDto(userEntityService.newUser(newUser));
    }
}
