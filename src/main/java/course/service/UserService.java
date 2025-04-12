package course.service;

import course.entity.UserEntity;
import course.enums.Role;
import course.exception.UserNotFoundException;
import course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public Long createUser(UserEntity userEntity) {
        userEntity.setRole(Role.ROLE_USER);
        return userRepository.save(userEntity).getId();
    }

    public void updateUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public void removeUser(Long id) {
        try {
            userRepository.delete(getUserById(id).orElseThrow(() -> new UserNotFoundException("Uncorrected user id")));
        }catch (UserNotFoundException e){
            System.err.println(e.getMessage());
        }
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

}
