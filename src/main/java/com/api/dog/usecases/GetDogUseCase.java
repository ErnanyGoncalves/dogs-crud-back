package com.api.dog.usecases;

import com.api.dog.dtos.DogDTO;
import com.api.dog.exceptions.DogNotFoundException;
import com.api.dog.mappers.DogMapper;
import com.api.dog.repositories.DogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetDogUseCase {

    private final DogRepository dogRepository;
    private final DogMapper dogMapper;

    public DogDTO getDog(Long id) {
        return dogRepository.findById(id).map(this.dogMapper::toDto).orElseThrow(() -> new DogNotFoundException(id));
    }
}
