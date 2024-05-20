package com.api.dog.adapters;

import com.api.dog.dtos.DogDTO;
import com.api.dog.models.Dog;
import com.api.dog.ports.DogApiPort;
import com.api.dog.services.DogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
@AllArgsConstructor
@Tag(name = "Dog Controller", description = "Dog APIs")
public class DogApiImpl implements DogApiPort {

    private final DogService dogService;

    @GetMapping
    @Operation(summary = "GET Dogs", description = "Returns a list of dogs.")
    public List<DogDTO> getDogs() {
        return dogService.getDogs();

    }

    @GetMapping("/{id}")
    @Operation(summary = "GET Dog", description = "Returns a dog from an existing id.")
    public DogDTO getDog(@PathVariable("id") Long id) {
        return dogService.getDog(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "POST Dog", description = "Creates a dog.")
    public void createDog(@RequestBody Dog dog) {
        dogService.createDog(dog);
    }

    @PutMapping("/{id}")
    @Operation(summary = "PUT Dog", description = "Updates a dog from an existing id.")
    public void editDog(@PathVariable("id") Long id, @RequestBody Dog newDogData) {
        dogService.editDog(id, newDogData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "DELETE Dog", description = "Deletes a dog from an existing id.")
    public void deleteDog(@PathVariable("id") Long id) {
        dogService.deleteDog(id);
    }
}
