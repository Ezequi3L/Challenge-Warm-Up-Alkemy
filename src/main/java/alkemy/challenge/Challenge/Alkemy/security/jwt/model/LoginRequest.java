package alkemy.challenge.Challenge.Alkemy.security.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class LoginRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
