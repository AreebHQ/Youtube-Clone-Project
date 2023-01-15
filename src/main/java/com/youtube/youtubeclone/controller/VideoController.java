package com.youtube.youtubeclone.controller;

import com.youtube.youtubeclone.service.VideoService;
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
//  @ResponseStatus(HttpStatus.CREATED)
  public void uploadVideo(@RequestParam("file") MultipartFile file){
    System.out.println("Called uplaod Video method in controller!");
    videoService.uploadVideo(file);
  }

  @GetMapping("/checking")
  public String check()
  {
    return "Success";
  }
}
