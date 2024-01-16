package com.ecommerce.EcommerceBackend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUpload {

    public String uploadFile(String path, MultipartFile file) throws RuntimeException{
        //get original file name
        String originalFileName = file.getOriginalFilename();
        //generate random image name
        String randomImageNameWithExtension = UUID.randomUUID().toString()
                .concat(originalFileName.substring(originalFileName.lastIndexOf(".")));
        String fullPath  = path+File.separator + randomImageNameWithExtension;
        File folderFile = new File(path);
        if(!folderFile.exists()){
            folderFile.mkdirs();
        }
        try{
            Files.copy(file.getInputStream(), Paths.get(fullPath));
        } catch (IOException e) {
           e.printStackTrace();
        }
        return randomImageNameWithExtension;
    }
}
