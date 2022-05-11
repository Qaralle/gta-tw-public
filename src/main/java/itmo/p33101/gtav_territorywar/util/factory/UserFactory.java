package itmo.p33101.gtav_territorywar.util.factory;

import itmo.p33101.gtav_territorywar.dto.AuthenticationRequestDto;
import itmo.p33101.gtav_territorywar.dto.RegisterDto;
import itmo.p33101.gtav_territorywar.model.UserEntity;

public class UserFactory implements EntityFactory<UserEntity, AuthenticationRequestDto> {
    @Override
    public UserEntity create(AuthenticationRequestDto o) {
        return null;
    }
}
