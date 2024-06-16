package com.api.dog.usecases;

import com.api.dog.models.Dog;
import com.api.dog.repositories.DogRepository;
import com.api.dog.services.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

@Service
@AllArgsConstructor
public class CreateDogUseCase {

    private final DogRepository dogRepository;
    private final S3Service s3Service;

    public void createDog(Dog dog, MultipartFile photo) throws IOException {

        String photoPath = System.getProperty("java.io.tmpdir") + "/" + photo.getOriginalFilename();
        File localFile = new File(photoPath);
        photo.transferTo(localFile);
        String newFileName = Instant.now() +"_"+dog.getName();
        s3Service.uploadFile( newFileName, photoPath);

        dog.setPhoto("http://localhost:4566/dog-bucket/" + newFileName);
        dogRepository.save(dog);
    }
}
