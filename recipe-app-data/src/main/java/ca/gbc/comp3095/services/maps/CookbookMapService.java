package ca.gbc.comp3095.services.maps;

import ca.gbc.comp3095.models.Cookbook;
import ca.gbc.comp3095.services.CookbookService;

import java.util.Set;

public class CookbookMapService extends AbstractMapService<Cookbook, Long> implements CookbookService {

        @Override
        public Set<Cookbook> findAll() {
            return super.findAll();
        }

        @Override
        public Cookbook findById(Long id) {
            return super.findById(id);
        }

        public Cookbook save(Cookbook object) {
            return super.save(object.getId(), object);
        }

        @Override
        public void delete(Cookbook object) {
            super.delete(object);
        }

        @Override
        public void deleteById(Long id) {
            super.deleteById(id);
        }
}
