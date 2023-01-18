package com.youtube.youtubeclone.controller;

import com.youtube.youtubeclone.model.UploadVideoResponse;
import com.youtube.youtubeclone.model.VideoDto;
import com.youtube.youtubeclone.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
public class VideoController {

  private final VideoService videoService;

  public VideoController(VideoService videoService) {
    this.videoService = videoService;
  }

  @PostMapping("/upload")
  @ResponseStatus(HttpStatus.CREATED)
  public UploadVideoResponse uploadVideo(@RequestParam("file") MultipartFile file){
    System.out.println("Called uplaod Video method in controller!");
    return videoService.uploadVideo(file);
  }

  @PostMapping("/thumbnail")
  @ResponseStatus(HttpStatus.CREATED)
  public String uploadThumbnail(@RequestParam("file") MultipartFile file, @RequestParam("videoId") String videoId){
    System.out.println("Called thumbnail method in controller!");
    return videoService.uploadThumbnail(file, videoId);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto){
    System.out.println("getting call in editVideo method!");
   return  videoService.editVideo(videoDto);
  }

}
