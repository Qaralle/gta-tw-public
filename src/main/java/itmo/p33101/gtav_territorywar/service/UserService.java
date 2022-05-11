package itmo.p33101.gtav_territorywar.service;

import itmo.p33101.gtav_territorywar.model.GangEntity;
import itmo.p33101.gtav_territorywar.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity register(UserEntity user,String name, String surname, int age, int gang);

    void touch(UserEntity user);

    List<UserEntity> getAll();

    UserEntity findByUsername(String username);

    UserEntity findById(int id);

    void delete(int id);

    UserEntity getCurrentUser();
}