package com.api.dog.repositories;

import com.api.dog.models.Dog;

import java.util.List;
import java.util.Optional;

public interface DogRepositoryPort {

    List<Dog> findAll();

    Optional<Dog> findById(Long id);

    void save(Dog dog);

    void edit(Long id, Dog dog);

    void delete(Long id);
}
