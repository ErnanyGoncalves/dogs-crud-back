package com.api.dog.adapters;

import com.api.dog.dtos.DogDTO;
import com.api.dog.models.Dog;
import com.api.dog.ports.DogApiPort;
import com.api.dog.services.DogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
@AllArgsConstructor
public class DogApiImpl implements DogApiPort {

    private final DogService dogService;

    @GetMapping
    public List<DogDTO> getDogs() {
        return dogService.getDogs();

    }

    @GetMapping("/{id}")
    public DogDTO getDog(@PathVariable("id") Long id) {
        return dogService.getDog(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createDog(@RequestBody Dog dog) {
        dogService.createDog(dog);
    }

    @PutMapping("/{id}")
    public void editDog(@PathVariable("id") Long id, @RequestBody Dog newDogData) {
        dogService.editDog(id, newDogData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDog(@PathVariable("id") Long id) {
        dogService.deleteDog(id);
    }
}
