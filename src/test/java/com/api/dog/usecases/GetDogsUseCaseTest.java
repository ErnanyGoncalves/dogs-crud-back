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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetDogsUseCaseTest {
    @Mock
    private DogRepositoryPort dogRepository;

    @Mock
    private DogMapper dogMapper;

    @InjectMocks
    private GetDogsUseCase getDogsUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDogs() {

        String name = "Dog name";
        Integer age = 1;
        Gender gender = Gender.MALE;
        String breed = "Dog breed";
        Float height = 5F;
        Float weight = 10F;
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        String about = "Some text";

        Dog mockDog1 = new Dog(1L, name, age,gender, breed,height,weight, photoUrl,about);
        Dog mockDog2 = new Dog(2L, name, age,gender, breed,height,weight, photoUrl,about);

        DogDTO mockDogDTO1 = new DogDTO(1L, name, age,gender, breed,height,weight, photoUrl,about);
        DogDTO mockDogDTO2 = new DogDTO(2L, name, age,gender, breed,height,weight, photoUrl,about);

        List<Dog> dogsFromRepo = Arrays.asList(
                mockDog1,mockDog2
        );
        List<DogDTO> expectedDogs = Arrays.asList(
                mockDogDTO1,mockDogDTO2
        );

        Map<Long, DogDTO> dogToDtoMap = new HashMap<>();
        dogToDtoMap.put(mockDog1.getId(), mockDogDTO1);
        dogToDtoMap.put(mockDog2.getId(), mockDogDTO2);

        when(dogRepository.findAll()).thenReturn(dogsFromRepo);

        when(dogMapper.toDto(Mockito.any(Dog.class))).thenAnswer(invocation -> {
            Dog dog = invocation.getArgument(0);
            return dogToDtoMap.get(dog.getId());
        });

        List<DogDTO> result = getDogsUseCase.getDogs();

        assertEquals(expectedDogs, result);
        Mockito.verify(dogRepository, Mockito.times(1)).findAll();
        Mockito.verify(dogMapper, Mockito.times(dogsFromRepo.size()))
                .toDto(Mockito.any(Dog.class));
    }
}