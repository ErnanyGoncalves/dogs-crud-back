package com.api.dog.adapters;

import com.api.dog.ports.S3ApiPort;
import com.api.dog.services.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/s3")
public class S3ApiImpl implements S3ApiPort {
    private final S3Service s3Service;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String key = file.getOriginalFilename();
        File localFile = new File(System.getProperty("java.io.tmpdir") + "/" + key);
        file.transferTo(localFile);
        s3Service.uploadFile(key, localFile.getAbsolutePath());
        return "Arquivo enviado com sucesso!";
    }

    @GetMapping("/download")
    public String downloadFile(@RequestParam("key") String key) {
        s3Service.downloadFile(key, "files/" + key);
        return "Arquivo baixado com sucesso! Salvo em: " + "files/" + key;
    }


    @GetMapping("/list")
    public List<String> listFiles() {
        return s3Service.listFiles();
    }

    @DeleteMapping("/delete/{key}")
    public String deleteFile(@PathVariable String key) {
        s3Service.deleteFile(key);
        return "Arquivo deletado com sucesso!";
    }
}
