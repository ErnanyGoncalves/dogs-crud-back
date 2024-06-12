package com.api.dog.ports;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface S3ApiPort {

    String uploadFile(MultipartFile file) throws IOException;

    String downloadFile(String key);

    List<String> listFiles();

    String deleteFile(String key);
}
