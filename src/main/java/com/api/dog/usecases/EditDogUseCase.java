package com.api.dog.usecases;

import com.api.dog.exceptions.DogNotFoundException;
import com.api.dog.models.Dog;
import com.api.dog.repositories.DogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EditDogUseCase {

    private final DogRepository dogRepository;

    public void editDog(Long id, Dog newDogData) {
        Optional<Dog> dog = dogRepository.findById(id);
        if (dog.isEmpty()) {
            throw new DogNotFoundException(id);
        }
        newDogData.setId(id);
        dogRepository.save(newDogData);
    }
}
