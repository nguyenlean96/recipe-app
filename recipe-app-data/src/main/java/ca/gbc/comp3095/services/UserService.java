package ca.gbc.comp3095.services;

import ca.gbc.comp3095.models.User;
import ca.gbc.comp3095.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // create, update, delete, find, findAll
    public User create(User object) {
        return userRepository.save(object);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User object) {
        return userRepository.save(object);
    }

    public void delete(User object) {
        userRepository.delete(object);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
