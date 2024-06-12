package com.api.dog.usecases;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ListFilesUseCase {
    private final AmazonS3 s3Client;
    private final String bucketName = "dog-bucket";

    public List<String> listFiles() {
        try {
            ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName);
            ListObjectsV2Result result = s3Client.listObjectsV2(req);
            return result.getObjectSummaries().stream()
                    .map(S3ObjectSummary::getKey)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar arquivos do bucket: " + e.getMessage(), e);
        }
    }
}
