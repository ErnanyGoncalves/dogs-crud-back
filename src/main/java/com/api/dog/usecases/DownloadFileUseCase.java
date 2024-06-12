package com.api.dog.usecases;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@AllArgsConstructor
public class DownloadFileUseCase {

    private final AmazonS3 s3Client;
    private final String bucketName = "dog-bucket";

    public void downloadFile(String key, String downloadPath) {
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));

        try (BufferedInputStream inputStream = new BufferedInputStream(object.getObjectContent());
             FileOutputStream fileOutputStream = new FileOutputStream(new File(downloadPath))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("Arquivo baixado com sucesso do bucket S3.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
