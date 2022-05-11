package itmo.p33101.gtav_territorywar.security.jwt;


import itmo.p33101.gtav_territorywar.model.Status;
import itmo.p33101.gtav_territorywar.model.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserEntity user) {
        return new JwtUser(
                user.getBanditId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(Collections.singletonList(user.getRole()))),
                user.getStatus().equals(Status.ACTIVE.toString())
        );
    }


    //TODO сделать нормальную таблицу ролей
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role)
                ).collect(Collectors.toList());
    }
}
