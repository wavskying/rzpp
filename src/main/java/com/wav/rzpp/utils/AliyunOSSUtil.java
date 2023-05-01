package com.wav.rzpp.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author: hbw
 **/
@Component
public class AliyunOSSUtil {
    @Value("${spring.aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${spring.aliyun.oss.access-key-id}")
    private String accessKeyId;
    @Value("${spring.aliyun.oss.access-key-secret}")
    private String accessKeySecret;
    @Value("${spring.aliyun.oss.bucket-name}")
    private String bucketName;

    /**
     * 上传文件到阿里云OSS
     *
     * @param file 待上传的文件
     * @return 上传后的文件URL
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        // 生成不重复的文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString().replaceAll("-", "") + suffix;

        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件到OSS
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filename, file.getInputStream());
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient
        ossClient.shutdown();

        // 返回文件URL
        return "https://" + bucketName + "." + endpoint + "/" + filename;
    }
}
