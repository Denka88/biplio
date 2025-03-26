package bip.online.biplio2023.impl;

import bip.online.biplio2023.dto.UserDto;
import bip.online.biplio2023.entity.Role;
import bip.online.biplio2023.entity.User;
import bip.online.biplio2023.repo.UserRepo;
import bip.online.biplio2023.service.UserService;
import jakarta.transaction.UserTransaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User save(UserDto data) {
        
        User user = new User();
        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setName(data.getName());
        user.setSurname(data.getSurname());
        user.setRoles(Collections.singleton(Role.USER));
        return userRepo.save(user);
    }


    @Override
    public void update(User data) {
        userRepo.save(data);
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public boolean usernameIsAvailable(String username) {
        return !userRepo.existsByUsername(username);
    }
}
