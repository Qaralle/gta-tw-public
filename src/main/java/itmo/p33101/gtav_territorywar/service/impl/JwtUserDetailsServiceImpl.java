package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.model.UserEntity;
import itmo.p33101.gtav_territorywar.repository.UserRepository;
import itmo.p33101.gtav_territorywar.security.jwt.JwtUser;
import itmo.p33101.gtav_territorywar.security.jwt.JwtUserFactory;
import itmo.p33101.gtav_territorywar.service.JwtUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {


    private final UserRepository userRepository;


    public JwtUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - {} successfully loaded", username);
        return jwtUser;
    }
}
