package com.youtube.youtubeclone.service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Component
public class S3Service implements FileService {

  public static final String AWS_BUCKET_NAME = "youtubeclones3storage";
  private final AmazonS3Client amazonS3Client;

  public S3Service(AmazonS3Client amazonS3Client) {
    this.amazonS3Client = amazonS3Client;
  }

  @Override
  public String uploadFile (MultipartFile file)
  {
   String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
   String key = UUID.randomUUID().toString() + filenameExtension;
   ObjectMetadata metadata = new ObjectMetadata();
   metadata.setContentLength(file.getSize());
   metadata.setContentType(file.getContentType());

    System.out.println("File type: " +file.getContentType());
    System.out.println("File Type from Meta Data: "+metadata.getContentType());

    try {
      amazonS3Client.putObject(AWS_BUCKET_NAME,key,file.getInputStream(),metadata);
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An exception occured while uploading hre file");
    }
    //throwing exception for not setting ACL
   // amazonS3Client.setObjectAcl(AWS_BUCKET_NAME,key,CannedAccessControlList.PublicRead);

    return amazonS3Client.getResourceUrl(AWS_BUCKET_NAME, key);
  }
}
