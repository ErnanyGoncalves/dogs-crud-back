package com.api.dog.usecases;

import com.api.dog.models.Dog;
import com.api.dog.repositories.DogRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EditDogUseCase {

    private final DogRepositoryPort dogRepository;

    public void editDog(Long id, Dog newDogData) {
        dogRepository.edit(id, newDogData);
    }
}
