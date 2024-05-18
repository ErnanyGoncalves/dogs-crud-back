package com.api.dog.adapters;

import com.api.dog.dtos.DogDTO;
import com.api.dog.models.Dog;
import com.api.dog.ports.DogApiPort;
import com.api.dog.repositories.DogRepositoryPort;
import com.api.dog.usecases.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
@AllArgsConstructor
public class DogApiImpl implements DogApiPort {

    private final GetDogsUseCase getDogsUseCase;
    private final GetDogUseCase getDogUseCase;
    private final CreateDogUseCase createDogUseCase;
    private final EditDogUseCase editDogUseCase;
    private final DeleteDogUseCase deleteDogUseCase;



    @GetMapping
    public List<DogDTO> getDogs() {
        return getDogsUseCase.getDogs();
    }

    @GetMapping("/{id}")
    public DogDTO getDog(@PathVariable("id") Long id) {
        return this.getDogUseCase.getDog(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createDog(@RequestBody Dog dog) {
        createDogUseCase.createDog(dog);
    }

    @PutMapping("/{id}")
    public void editDog(@PathVariable("id") Long id, @RequestBody Dog newDogData) {
        editDogUseCase.editDog(id,newDogData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDog(@PathVariable("id") Long id) {
        deleteDogUseCase.deleteDog(id);
    }
}
