package itmo.p33101.gtav_territorywar.rest;


import itmo.p33101.gtav_territorywar.dto.RefreshTokenDto;
import itmo.p33101.gtav_territorywar.model.UserEntity;
import itmo.p33101.gtav_territorywar.security.jwt.JwtTokenProvider;
import itmo.p33101.gtav_territorywar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/refresh/")
public class RefreshTokenController {


    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RefreshTokenController(JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping("token")
    public ResponseEntity test(@RequestBody RefreshTokenDto req){

        String username = new String(Base64.getDecoder().decode(req.getRefresh_token())).split("&")[1];
        UserEntity user = userService.findByUsername(username);
        Map<Object, Object> response = new HashMap<>();

        System.out.println(req.getRefresh_token());
        System.out.println(user.getRefreshToken());

        if (req.getRefresh_token().equals(user.getRefreshToken())){

            String token = jwtTokenProvider.createToken(username);
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            response.put("token", token);
            response.put("username", username);
            response.put("refresh_token", user.getRefreshToken());

        } else {
            throw new BadCredentialsException("Invalid refresh token");
        }


        return ResponseEntity.ok(response);
    }
}
