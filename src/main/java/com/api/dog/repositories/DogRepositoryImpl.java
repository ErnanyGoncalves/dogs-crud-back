package com.api.dog.repositories;

import com.api.dog.models.Dog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DogRepositoryImpl implements DogRepositoryPort {
    
    private final DogRepository dogRepository;

    @Override
    public List<Dog> findAll() {
        return dogRepository.findAll();
    }

    @Override
    public Optional<Dog> findById(Long id) {
        return dogRepository.findById(id);
    }

    @Override
    public void save(Dog dog) {
        dogRepository.save(dog);
    }

    @Override
    public void edit(Long id, Dog dog) {
        dog.setId(id);
        dogRepository.save(dog);
    }

    @Override
    public void delete(Long id) {
        dogRepository.deleteById(id);
    }
}
