package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.model.GangEntity;
import itmo.p33101.gtav_territorywar.model.Status;
import itmo.p33101.gtav_territorywar.model.UserEntity;
import itmo.p33101.gtav_territorywar.repository.UserRepository;
import itmo.p33101.gtav_territorywar.service.BanditService;
import itmo.p33101.gtav_territorywar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BanditService banditService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BanditService banditService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.banditService = banditService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override

    public UserEntity register(UserEntity user,String name, String surname, int age, int gang) {

        int id = banditService.register(name, surname, age, gang).getId();

        user.setRole("USER");
        user.setRefreshToken(Base64.getEncoder().encodeToString((UUID.randomUUID().toString()+"&"+user.getUsername()).getBytes()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE.toString());
        user.setBanditId(id);
        System.out.println(user.getRole()+"   "+user.getPassword()+"   "+user.getStatus()+"   "+user.getBanditId());

        return userRepository.save(user);
    }


    @Override
    public void touch(UserEntity user) {
        user.setLastAccessTime(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAll() {
        return null;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity findById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public UserEntity getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserEntity user = findByUsername(username);
        return user;
    }
}
