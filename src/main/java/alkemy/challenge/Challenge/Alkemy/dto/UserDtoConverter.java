package alkemy.challenge.Challenge.Alkemy.dto;

import alkemy.challenge.Challenge.Alkemy.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public GetUserDto convertUserEntityToGetUserDto(UserEntity user) {
        return GetUserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

}