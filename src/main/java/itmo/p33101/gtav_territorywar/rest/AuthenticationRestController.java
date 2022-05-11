package itmo.p33101.gtav_territorywar.rest;

import itmo.p33101.gtav_territorywar.dto.AuthenticationRequestDto;
import itmo.p33101.gtav_territorywar.dto.RegisterDto;
import itmo.p33101.gtav_territorywar.model.UserEntity;
import itmo.p33101.gtav_territorywar.security.jwt.JwtTokenProvider;
import itmo.p33101.gtav_territorywar.service.GangService;
import itmo.p33101.gtav_territorywar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NonUniqueResultException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth/")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final GangService gangService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, GangService gangService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.gangService = gangService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            UserEntity user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            String token = jwtTokenProvider.createToken(username);

            userService.touch(user);
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("refresh_token", user.getRefreshToken());
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (UsernameNotFoundException e) {
            Map<Object, Object> response = new HashMap<>();
            response.put("description","user with username: "+requestDto.getUsername()+" not found");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

        } catch (AuthenticationException e){
            Map<Object, Object> response = new HashMap<>();
            response.put("description", "Wrong username or password");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

        } catch (Exception e){
            Map<Object, Object> response = new HashMap<>();
            response.put("description", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterDto requestDto) {
        try {
            String username = requestDto.getUsername();
            if (userService.findByUsername(username) != null){
                throw new NonUniqueResultException("User has already registered");
            }

            UserEntity user=userService.register(new UserEntity(username,requestDto.getPassword())
                    ,requestDto.getName()
                    ,requestDto.getLastname()
                    ,requestDto.getAge()
                    ,requestDto.getGang());

            String token = jwtTokenProvider.createToken(username);
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);


            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("refresh_token", user.getRefreshToken());
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (IncorrectResultSizeDataAccessException | NonUniqueResultException ex) {
            Map<Object, Object> response = new HashMap<>();
            response.put("description","user with username: "+requestDto.getUsername() +" has already registered");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

}
