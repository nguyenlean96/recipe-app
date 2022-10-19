package ca.gbc.comp3095.services.maps;

import ca.gbc.comp3095.models.User;
import ca.gbc.comp3095.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserMapService extends AbstractMapService<User, Long> implements UserService {

    @Override
    public Set<User> findAll() {
        return super.findAll();
    }

    @Override
    public User findById(Long id) {
        return super.findById(id);
    }

    public User save(User object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(User object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
