package com.api.dog.usecases;

import com.api.dog.dtos.DogDTO;
import com.api.dog.exceptions.DogNotFoundException;
import com.api.dog.models.Dog;
import com.api.dog.repositories.DogRepository;
import com.api.dog.services.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteDogUseCase {
    private final DogRepository dogRepository;
    private final S3Service s3Service;

    public void deleteDog(Long id) {
        Optional<Dog> dog = dogRepository.findById(id);
        if (dog.isEmpty()) {
            throw new DogNotFoundException(id);
        }

        s3Service.deleteFile(dog.get().getPhoto().split("/dog-bucket/")[1]);
        dogRepository.deleteById(id);
    }
}
