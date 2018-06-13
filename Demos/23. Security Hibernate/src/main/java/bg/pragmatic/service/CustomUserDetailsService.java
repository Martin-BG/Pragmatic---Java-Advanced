package bg.pragmatic.service;

import bg.pragmatic.model.CustomUserDetails;
import bg.pragmatic.model.User;
import bg.pragmatic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository usersRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByName(username);

        user
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return user
                .map(CustomUserDetails::new).get();
    }
}
