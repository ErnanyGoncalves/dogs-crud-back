package com.api.dog.services;

import com.api.dog.usecases.DeleteFileUseCase;
import com.api.dog.usecases.DownloadFileUseCase;
import com.api.dog.usecases.ListFilesUseCase;
import com.api.dog.usecases.UploadFileUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class S3Service {

    private final UploadFileUseCase uploadFileUseCase;
    private final DownloadFileUseCase downloadFileUseCase;
    private final ListFilesUseCase listFilesUseCase;
    private final DeleteFileUseCase deleteFileUseCase;

    public void uploadFile(String key, String filePath) {
        this.uploadFileUseCase.uploadFile(key, filePath);
    }

    public void downloadFile(String key, String filePath) {
        this.downloadFileUseCase.downloadFile(key, filePath);
    }

    public List<String> listFiles() {
        return this.listFilesUseCase.listFiles();
    }

    public void deleteFile(String key) {
        this.deleteFileUseCase.deleteFile(key);
    }
}
