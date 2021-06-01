package alkemy.challenge.Challenge.Alkemy.security.jwt;

import alkemy.challenge.Challenge.Alkemy.model.UserEntity;
import alkemy.challenge.Challenge.Alkemy.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final MyUserDetailsService userDetailsService;

    private String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(JwtTokenProvider.TOKEN_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtTokenProvider.TOKEN_PREFIX))
            return bearerToken.substring(JwtTokenProvider.TOKEN_PREFIX.length(), bearerToken.length());
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getJwtFromRequest(request);

            if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
                Long userId = tokenProvider.getUserIdFromJWT(token);
                UserEntity user = (UserEntity) userDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex){
            log.info("Unable to establish user authentication in security context");
        }

        filterChain.doFilter(request, response);
    }
}
