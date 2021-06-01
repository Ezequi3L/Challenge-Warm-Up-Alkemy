package alkemy.challenge.Challenge.Alkemy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userEntityService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        return userEntityService.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with ID: " + id + " not found"));
    }
}
