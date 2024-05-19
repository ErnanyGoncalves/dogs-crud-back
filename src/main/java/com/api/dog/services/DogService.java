package com.api.dog.services;

import com.api.dog.dtos.DogDTO;
import com.api.dog.models.Dog;
import com.api.dog.usecases.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DogService {

    private final GetDogsUseCase getDogsUseCase;
    private final GetDogUseCase getDogUseCase;
    private final CreateDogUseCase createDogUseCase;
    private final EditDogUseCase editDogUseCase;
    private final DeleteDogUseCase deleteDogUseCase;

    public List<DogDTO> getDogs() {

        return getDogsUseCase.getDogs();
    }

    public DogDTO getDog(Long id) {
        return getDogUseCase.getDog(id);
    }

    public void createDog(Dog dog) {
        createDogUseCase.createDog(dog);
    }

    public void editDog(Long id, Dog dog) {
        editDogUseCase.editDog(id, dog);
    }

    public void deleteDog(Long id) {
        deleteDogUseCase.deleteDog(id);
    }
}
