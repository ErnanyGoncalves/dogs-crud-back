package com.api.dog.usecases;

import com.api.dog.dtos.DogDTO;
import com.api.dog.mappers.DogMapper;
import com.api.dog.repositories.DogRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetDogsUseCase {

    private final DogRepositoryPort dogRepository;
    private final DogMapper dogMapper;

    public List<DogDTO> getDogs() {
        return dogRepository.findAll().stream()
                .map(this.dogMapper::toDto).toList();
    }
}
