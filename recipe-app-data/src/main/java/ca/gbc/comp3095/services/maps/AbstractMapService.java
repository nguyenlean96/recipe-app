package ca.gbc.comp3095.services.maps;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AbstractMapService <T, ID> {
    protected Map<ID, T> map;

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return this.map.get(id);
    }

    T save(ID id, T object) {
        map.put(id, object);
        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }
}
