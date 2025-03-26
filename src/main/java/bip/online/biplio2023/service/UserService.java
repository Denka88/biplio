package bip.online.biplio2023.service;

import bip.online.biplio2023.dto.UserDto;
import bip.online.biplio2023.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    List<User> findAllUsers();

    User findByUsername(String username);

    User save(UserDto data);

    void update(User data);

    void delete(Long id);
    
    boolean usernameIsAvailable(String username);
}
