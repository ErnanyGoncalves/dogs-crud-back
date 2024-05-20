package com.api.dog.adapters;

import com.api.dog.dtos.DogDTO;
import com.api.dog.enums.Gender;
import com.api.dog.services.DogService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

}