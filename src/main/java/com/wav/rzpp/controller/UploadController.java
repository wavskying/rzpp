package com.wav.rzpp.controller;

import com.wav.rzpp.common.AjaxResult;
import com.wav.rzpp.utils.AliyunOSSUtil;
import io.swagger.annotations.Api;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author: hbw
 **/
@CrossOrigin
@Api("图片上传")
@Validated
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    /*@PostMapping("/image")
    public AjaxResult uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String folderPath = "E:\\vue-admin-system-master\\src\\components\\common\\img"; // folder path to save images
        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename); // extract file extension
        String uniqueFilename = generateUniqueFilename() + "." + extension; // generate unique filename with extension

        Path targetPath = Paths.get(folderPath + uniqueFilename);
        Files.copy(file.getInputStream(), targetPath); // save file to target path

        return AjaxResult.success(uniqueFilename); // return filename as response
    }

    private String generateUniqueFilename() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String randomString = UUID.randomUUID().toString();
        return timestamp + "-" + randomString;
    }*/
    @PostMapping("/image")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return aliyunOSSUtil.uploadFile(file);
    }
}
