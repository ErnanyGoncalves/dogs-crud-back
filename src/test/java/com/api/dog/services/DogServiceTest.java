package com.api.dog.services;

import com.api.dog.dtos.DogDTO;
import com.api.dog.enums.Gender;
import com.api.dog.models.Dog;
import com.api.dog.usecases.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DogServiceTest {

    @Mock
    private GetDogsUseCase getDogsUseCase;
    @Mock
    private GetDogUseCase getDogUseCase;
    @Mock
    private CreateDogUseCase createDogUseCase;
    @Mock
    private EditDogUseCase editDogUseCase;
    @Mock
    private DeleteDogUseCase deleteDogUseCase;

    @InjectMocks
    private DogService dogService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDogsUseCase(){
        String name = "Dog name";
        Integer age = 1;
        Gender gender = Gender.MALE;
        String breed = "Dog breed";
        Float height = 5F;
        Float weight = 10F;
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        String about = "Some text";

        DogDTO mockDogDTO1 = new DogDTO(1L, name, age,gender, breed,height,weight, photoUrl,about);
        DogDTO mockDogDTO2 = new DogDTO(2L, name, age,gender, breed,height,weight, photoUrl,about);

        List<DogDTO> mockList = Arrays.asList(
                mockDogDTO1,mockDogDTO2
        );

        when(getDogsUseCase.getDogs()).thenReturn(mockList);

        List<DogDTO> result = dogService.getDogs();

        assertEquals(mockList, result);
        verify(getDogsUseCase, times(1)).getDogs();
    }

    @Test
    public void testGetDogUseCase(){
        Long id = 1L;
        String name = "Dog name";
        Integer age = 1;
        Gender gender = Gender.MALE;
        String breed = "Dog breed";
        Float height = 5F;
        Float weight = 10F;
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        String about = "Some text";



        DogDTO mockDog = new DogDTO(id, name, age,gender, breed,height,weight, photoUrl,about);
        when(getDogUseCase.getDog(id)).thenReturn(mockDog);

        DogDTO result = dogService.getDog(id);

        verify(getDogUseCase, times(1)).getDog(id);
        assertEquals(mockDog, result);
    }

    @Test
    public void testGetDogUseCaseNotFound(){
        Long id = 1L;
        when(getDogUseCase.getDog(id)).thenThrow(new NoSuchElementException());

        assertThrows(NoSuchElementException.class, () -> dogService.getDog(id));
        verify(getDogUseCase, times(1)).getDog(id);
    }

    @Test
    public void testCreateDogUseCase(){
        String name = "Dog name";
        Integer age = 1;
        Gender gender = Gender.MALE;
        String breed = "Dog breed";
        Float height = 5F;
        Float weight = 10F;
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        String about = "Some text";

        Dog mockDog = new Dog(null, name, age,gender, breed,height,weight, photoUrl,about);

        dogService.createDog(mockDog);

        verify(createDogUseCase, times(1)).createDog(mockDog);
    }

    @Test
    public void testEditDogUseCase(){
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

        dogService.editDog(id,mockDog);

        verify(editDogUseCase, times(1)).editDog(id,mockDog);
    }

    @Test
    public void testDeleteDogUseCase(){
        Long id = 1L;
        dogService.deleteDog(id);

        verify(deleteDogUseCase, times(1)).deleteDog(id);
    }
}