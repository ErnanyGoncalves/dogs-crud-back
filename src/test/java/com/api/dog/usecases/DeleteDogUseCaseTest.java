package com.api.dog.usecases;

import com.api.dog.enums.Gender;
import com.api.dog.models.Dog;
import com.api.dog.repositories.DogRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class DeleteDogUseCaseTest {
    @Mock
    private DogRepositoryPort dogRepository;

    @InjectMocks
    private DeleteDogUseCase deleteDogUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteDog() {
        Long id = 1L;
        deleteDogUseCase.deleteDog(id);

        Mockito.verify(dogRepository, Mockito.times(1)).delete(id);
    }
}