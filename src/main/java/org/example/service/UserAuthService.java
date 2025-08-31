package org.example.service;

import org.example.domain.UserAuthEntity;
import org.example.repository.UserAuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserAuthService implements UserDetailsService {
    private final UserAuthRepository userAuthRepository;

    public UserAuthService(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    public UserDetails save(UserAuthEntity userAuth) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.computeIfAbsent("key", k -> "val");
        return userAuthRepository.save(userAuth);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
