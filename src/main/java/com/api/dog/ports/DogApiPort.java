package com.api.dog.ports;

import com.api.dog.dtos.DogDTO;
import com.api.dog.models.Dog;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DogApiPort {

    List<DogDTO> getDogs();

    DogDTO getDog(Long id);

    void createDog(Dog dog, MultipartFile photo) throws IOException;

    void editDog(Long id, Dog newDogData);

    void deleteDog(Long id);
}
