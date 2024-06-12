package com.api.dog.usecases;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
public class UploadFileUseCase {
    private final AmazonS3 s3Client;
    private final String bucketName = "dog-bucket";

    public void uploadFile(String key, String filePath) {

        File file = new File(filePath);
        s3Client.putObject(new PutObjectRequest(bucketName, key, file));
        System.out.println("Arquivo enviado com sucesso para o bucket S3.");
    }
}
