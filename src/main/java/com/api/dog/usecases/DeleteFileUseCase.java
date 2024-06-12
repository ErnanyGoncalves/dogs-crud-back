package com.api.dog.usecases;

import com.amazonaws.services.s3.AmazonS3;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteFileUseCase {

    private final AmazonS3 s3Client;
    private final String bucketName = "dog-bucket";

    public void deleteFile(String key) {
        s3Client.deleteObject(bucketName, key);

    }
}
