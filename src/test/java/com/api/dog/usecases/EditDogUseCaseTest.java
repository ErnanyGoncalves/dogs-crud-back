package com.api.dog.usecases;

import com.api.dog.enums.Gender;
import com.api.dog.exceptions.DogNotFoundException;
import com.api.dog.models.Dog;
import com.api.dog.repositories.DogRepository;
import com.api.dog.repositories.DogRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EditDogUseCaseTest {

        @Mock
        private DogRepository dogRepository;

        @InjectMocks
        private EditDogUseCase editDogUseCase;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testEditDog() {
            Long id = 1L;
            String name = "Dog name";
            Integer age = 1;
            Gender gender = Gender.MALE;
            String breed = "Dog breed";
            Float height = 5F;
            Float weight = 10F;
            String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
            String about = "Some text";

            Dog mockDog = new Dog(null, name, age,gender, breed,height,weight, photoUrl,about);

            Dog existingDog = new Dog(id, name, age, gender, breed, height, weight, photoUrl, about);

            when(dogRepository.findById(id)).thenReturn(Optional.of(existingDog));

            editDogUseCase.editDog(id, mockDog);

            verify(dogRepository, times(1)).findById(id);
            verify(dogRepository, times(1)).save(mockDog);
            assert(mockDog.getId().equals(id));

        }

    @Test
    public void testEditDogThrowsDogNotFoundException() {
        Long id = 1L;
        String name = "Dog name";
        Integer age = 1;
        Gender gender = Gender.MALE;
        String breed = "Dog breed";
        Float height = 5F;
        Float weight = 10F;
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        String about = "Some text";

        Dog mockDog = new Dog(null, name, age, gender, breed, height, weight, photoUrl, about);

        when(dogRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DogNotFoundException.class, () -> editDogUseCase.editDog(id, mockDog));

        verify(dogRepository, times(1)).findById(id);
        verify(dogRepository, never()).save(mockDog);
    }
    }