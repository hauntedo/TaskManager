package ru.itis.taskmanager.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.taskmanager.exception.UserNotFoundException;
import ru.itis.taskmanager.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AccountUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AccountUserDetails(userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
