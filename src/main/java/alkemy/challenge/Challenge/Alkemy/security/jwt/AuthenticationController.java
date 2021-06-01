package alkemy.challenge.Challenge.Alkemy.security.jwt;

import alkemy.challenge.Challenge.Alkemy.dto.GetUserDto;
import alkemy.challenge.Challenge.Alkemy.dto.UserDtoConverter;
import alkemy.challenge.Challenge.Alkemy.model.UserEntity;
import alkemy.challenge.Challenge.Alkemy.security.jwt.model.JwtUserResponse;
import alkemy.challenge.Challenge.Alkemy.security.jwt.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserDtoConverter converter;

    @PostMapping("auth/login")
    public ResponseEntity<JwtUserResponse> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity user = (UserEntity) authentication.getPrincipal();
        String jwtToken = tokenProvider.generateToken(authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertUserEntityAndTokenToJwtUserResponse(user, jwtToken));
    }

    public GetUserDto me(@AuthenticationPrincipal UserEntity user){
        return converter.convertUserEntityToGetUserDto(user);
    }

    private JwtUserResponse convertUserEntityAndTokenToJwtUserResponse(UserEntity user, String jwtToken) {
        return JwtUserResponse
                .jwtUserResponseBuilder()
                .username(user.getUsername())
                .email(user.getEmail())
                .token(jwtToken)
                .build();
    }
}
