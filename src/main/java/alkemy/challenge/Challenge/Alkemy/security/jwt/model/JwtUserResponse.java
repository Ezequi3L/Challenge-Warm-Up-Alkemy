package alkemy.challenge.Challenge.Alkemy.security.jwt.model;

import alkemy.challenge.Challenge.Alkemy.dto.GetUserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends GetUserDto {

    private String token;

    @Builder(builderMethodName = "jwtUserResponseBuilder")
    public JwtUserResponse(String username, String email, String token) {
        super(username, email);
        this.token = token;
    }
}
