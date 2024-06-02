package com.api.dog.usecases;

import com.api.dog.exceptions.DogNotFoundException;
import com.api.dog.models.Dog;
import com.api.dog.repositories.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteDogUseCaseTest {
    @Mock
    private DogRepository dogRepository;

    @InjectMocks
    private DeleteDogUseCase deleteDogUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteDog() {
        Long id = 1L;

        Dog dog = new Dog();
        dog.setId(id);

        when(dogRepository.findById(id)).thenReturn(Optional.of(dog));

        deleteDogUseCase.deleteDog(id);

        verify(dogRepository, times(1)).findById(id);
        verify(dogRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteDogThrowsDogNotFoundException() {
        Long id = 1L;

        when(dogRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DogNotFoundException.class, () -> deleteDogUseCase.deleteDog(id));

        verify(dogRepository, times(1)).findById(id);
        verify(dogRepository, never()).deleteById(id);
    }
}