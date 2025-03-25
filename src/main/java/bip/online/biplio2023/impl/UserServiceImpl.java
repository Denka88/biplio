package bip.online.biplio2023.impl;

import bip.online.biplio2023.entity.User;
import bip.online.biplio2023.repo.UserRepo;
import bip.online.biplio2023.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
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
    public User save(User data) {
        return userRepo.save(data);
    }

    @Override
    public void update(User data) {
        userRepo.save(data);
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}
