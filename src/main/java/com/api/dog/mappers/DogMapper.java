package com.api.dog.mappers;

import com.api.dog.dtos.DogDTO;
import com.api.dog.models.Dog;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DogMapper {

    DogDTO toDto(Dog dog);
}