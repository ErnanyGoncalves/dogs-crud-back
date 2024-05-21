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

class CreateDogUseCaseTest {

    @Mock
    private DogRepositoryPort dogRepository;

    @InjectMocks
    private CreateDogUseCase createDogUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateDog() {
        String name = "Dog name";
        Integer age = 1;
        Gender gender = Gender.MALE;
        String breed = "Dog breed";
        Float height = 5F;
        Float weight = 10F;
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        String about = "Some text";

        Dog mockDog = new Dog(null, name, age,gender, breed,height,weight, photoUrl,about);

        createDogUseCase.createDog(mockDog);

        Mockito.verify(dogRepository, Mockito.times(1)).save(mockDog);
    }
}