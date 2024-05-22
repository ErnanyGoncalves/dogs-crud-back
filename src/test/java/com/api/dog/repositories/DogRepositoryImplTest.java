package com.api.dog.repositories;

import com.api.dog.dtos.DogDTO;
import com.api.dog.enums.Gender;
import com.api.dog.models.Dog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DogRepositoryImplTest {

    @Mock
    private DogRepository dogRepository;

    @InjectMocks
    private DogRepositoryImpl dogRepositoryImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testFindAll() {
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

        List<Dog> mockList = Arrays.asList(
                mockDog1,mockDog2
        );

        when(dogRepository.findAll()).thenReturn(mockList);

        List<Dog> result = dogRepositoryImpl.findAll();

        assertEquals(mockList, result);
        verify(dogRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Long id = 1L;
        String name = "Dog name";
        Integer age = 1;
        Gender gender = Gender.MALE;
        String breed = "Dog breed";
        Float height = 5F;
        Float weight = 10F;
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        String about = "Some text";

        Dog mockDog = new Dog(id, name, age,gender, breed,height,weight, photoUrl,about);
        when(dogRepository.findById(id)).thenReturn(Optional.of(mockDog));

        Optional<Dog> result = dogRepositoryImpl.findById(id);

        assertEquals(Optional.of(mockDog), result);
        verify(dogRepository, times(1)).findById(id);
    }


    @Test
    void testFindByIdNotFound() {
        Long id = 1L;
        when(dogRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Dog> result = dogRepositoryImpl.findById(id);

        assertTrue(result.isEmpty());
        verify(dogRepository, times(1)).findById(id);
    }


    @Test
    void testSave() {
        String name = "Dog name";
        Integer age = 1;
        Gender gender = Gender.MALE;
        String breed = "Dog breed";
        Float height = 5F;
        Float weight = 10F;
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        String about = "Some text";

        Dog mockDog = new Dog(null, name, age,gender, breed,height,weight, photoUrl,about);


        dogRepositoryImpl.save(mockDog);

        verify(dogRepository, times(1)).save(mockDog);
    }

    @Test
    void testEdit() {
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
        dogRepositoryImpl.edit(id, mockDog);

        assertEquals(id, mockDog.getId());
        verify(dogRepository, times(1)).save(mockDog);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        dogRepositoryImpl.delete(id);

        verify(dogRepository, times(1)).deleteById(id);
    }

}