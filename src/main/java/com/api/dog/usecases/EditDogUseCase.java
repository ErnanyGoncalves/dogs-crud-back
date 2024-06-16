package com.api.dog.usecases;

import com.api.dog.exceptions.DogNotFoundException;
import com.api.dog.models.Dog;
import com.api.dog.repositories.DogRepository;
import com.api.dog.services.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EditDogUseCase {

    private final DogRepository dogRepository;
    private final S3Service s3Service;

    public void editDog(Long id, Dog newDogData, MultipartFile photo) throws IOException {
        Optional<Dog> dog = dogRepository.findById(id);
        if (dog.isEmpty()) {
            throw new DogNotFoundException(id);
        }
        if(photo!=null){
            s3Service.deleteFile(dog.get().getPhoto().split("/dog-bucket/")[1]);

            String photoPath = System.getProperty("java.io.tmpdir") + "/" + photo.getOriginalFilename();
            File localFile = new File(photoPath);
            photo.transferTo(localFile);
            String newFileName = Instant.now() +"_"+dog.get().getName();
            s3Service.uploadFile( newFileName, photoPath);

            newDogData.setPhoto("http://localhost:4566/dog-bucket/" + newFileName);
        }else{
            newDogData.setPhoto(dog.get().getPhoto());
        }
        newDogData.setId(id);
        dogRepository.save(newDogData);
    }
}
