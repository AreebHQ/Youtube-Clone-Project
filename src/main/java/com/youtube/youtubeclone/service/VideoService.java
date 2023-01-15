package com.youtube.youtubeclone.service;

import com.youtube.youtubeclone.model.Video;
import com.youtube.youtubeclone.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoService {

  private final S3Service s3Service;
  private final VideoRepository videoRepository;

  public VideoService(S3Service s3Service, VideoRepository videoRepository) {
    this.s3Service = s3Service;
    this.videoRepository = videoRepository;
  }

  public void uploadVideo(MultipartFile multipartFile)
  {
    String videoUrl = s3Service.uploadFile(multipartFile);
    Video video = new Video();
    video.setVideoUrl(videoUrl);
    videoRepository.save(video);
  }

}
