package com.api.dog.usecases;

import com.api.dog.repositories.DogRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteDogUseCase {
    private final DogRepositoryPort dogRepository;

    public void deleteDog(Long id) {
        dogRepository.delete(id);
    }
}
