package com.api.dog.usecases;

import com.api.dog.models.Dog;
import com.api.dog.repositories.DogRepository;
import com.api.dog.repositories.DogRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateDogUseCase {

    private final DogRepository dogRepository;

    public void createDog(Dog dog) {
        dogRepository.save(dog);
    }
}
