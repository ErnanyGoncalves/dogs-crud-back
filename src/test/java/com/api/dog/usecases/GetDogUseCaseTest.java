package com.api.dog.usecases;

import com.api.dog.dtos.DogDTO;
import com.api.dog.enums.Gender;
import com.api.dog.mappers.DogMapper;
import com.api.dog.models.Dog;
import com.api.dog.repositories.DogRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GetDogUseCaseTest {

    @Mock
    private DogRepositoryPort dogRepository;

    @Mock
    private DogMapper dogMapper;

    @InjectMocks
    private GetDogUseCase getDogUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetDog() {
        Long id = 1L;
        String name = "Dog name";
        Integer age = 1;
        Gender gender = Gender.MALE;
        String breed = "Dog breed";
        Float height = 5F;
        Float weight = 10F;
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        String about = "Some text";

        Dog dog = new Dog();

        DogDTO mockDog = new DogDTO(id, name, age,gender, breed,height,weight, photoUrl,about);

        Mockito.when(dogRepository.findById(id)).thenReturn(Optional.of(dog));
        Mockito.when(dogMapper.toDto(dog)).thenReturn(mockDog);

        DogDTO result = getDogUseCase.getDog(id);

        assertEquals(mockDog, result);
        Mockito.verify(dogRepository, Mockito.times(1)).findById(id);
        Mockito.verify(dogMapper, Mockito.times(1)).toDto(dog);


    }

    @Test
    public void testGetDogNotFound() {

        Long id = 1L;
        Mockito.when(dogRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> getDogUseCase.getDog(id));
        Mockito.verify(dogRepository, Mockito.times(1)).findById(id);
        Mockito.verifyNoInteractions(dogMapper);

    }
}