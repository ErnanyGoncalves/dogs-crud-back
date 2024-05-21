package com.api.dog.adapters;

import com.api.dog.dtos.DogDTO;
import com.api.dog.enums.Gender;
import com.api.dog.models.Dog;
import com.api.dog.services.DogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class DogApiImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DogService dogService;

    @Test
    public void testGetDogs() throws Exception {

        Long id = 1L;
        String name = "Dog name";
        Integer age = 1;
        Gender gender = Gender.MALE;
        String breed = "Dog breed";
        Float height = 5F;
        Float weight = 10F;
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        String about = "Some text";

        List<DogDTO> mockDogs = Collections.singletonList(
                new DogDTO(id, name, age,gender, breed,height,weight, photoUrl,about));


        Mockito.when(
                        dogService.getDogs())
                .thenReturn(mockDogs);

        mockMvc.perform(get("/dogs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(id.intValue()))
                .andExpect(jsonPath("$[0].name").value(name))
                .andExpect(jsonPath("$[0].age").value(age.intValue()))
                .andExpect(jsonPath("$[0].gender").value(gender.getValue()))
                .andExpect(jsonPath("$[0].breed").value(breed))
                .andExpect(jsonPath("$[0].height").value(height.floatValue()))
                .andExpect(jsonPath("$[0].weight").value(weight.floatValue()))
                .andExpect(jsonPath("$[0].photo").value(photoUrl))
                .andExpect(jsonPath("$[0].about").value(about));

    }

    @Test
    public void testGetDog() throws Exception{
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

        Mockito.when(dogService.getDog(id))
                .thenReturn(mockDog);

        mockMvc.perform(get("/dogs/"+id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id.intValue()))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age.intValue()))
                .andExpect(jsonPath("$.gender").value(gender.getValue()))
                .andExpect(jsonPath("$.breed").value(breed))
                .andExpect(jsonPath("$.height").value(height.floatValue()))
                .andExpect(jsonPath("$.weight").value(weight.floatValue()))
                .andExpect(jsonPath("$.photo").value(photoUrl))
                .andExpect(jsonPath("$.about").value(about));
    }

    @Test
    public void testPostDog() throws Exception{

        String name = "Dog name";
        Integer age = 1;
        Gender gender = Gender.MALE;
        String breed = "Dog breed";
        Float height = 5F;
        Float weight = 10F;
        String photoUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg";
        String about = "Some text";

        Dog mockDog = new Dog(null, name, age,gender, breed,height,weight, photoUrl,about);

        mockMvc.perform(post("/dogs")
                        .content(new ObjectMapper().writeValueAsString(mockDog))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Mockito.verify(dogService).createDog(mockDog);
    }

    @Test
    public void testPutDog() throws Exception {
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


        mockMvc.perform(put("/dogs/"+id)
                        .content(new ObjectMapper().writeValueAsString(mockDog))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(dogService).editDog(id, mockDog);
    }

    @Test
    public void testDeleteDog() throws Exception {

        Long id = 1L;

        mockMvc.perform(delete("/dogs/"+id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(dogService).deleteDog(id);
    }

}