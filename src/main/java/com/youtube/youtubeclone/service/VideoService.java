package com.youtube.youtubeclone.service;

import com.youtube.youtubeclone.model.UploadVideoResponse;
import com.youtube.youtubeclone.model.Video;
import com.youtube.youtubeclone.model.VideoDto;
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

  public UploadVideoResponse uploadVideo(MultipartFile multipartFile)
  {
    String videoUrl = s3Service.uploadFile(multipartFile);
    Video video = new Video();
    video.setVideoUrl(videoUrl);
    Video savedVideo = videoRepository.save(video);
    return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());
  }

  public VideoDto editVideo(VideoDto videoDto)
  {
    Video savedVideo = getVideoById(videoDto.getId());
    savedVideo.setTitle(videoDto.getTitle());
    savedVideo.setDescription(videoDto.getDescription());
    savedVideo.setTags(videoDto.getTags());
    savedVideo.setThumbnail(videoDto.getThumbnailUrl());
    savedVideo.setVideStatus(videoDto.getVideoStatus());

    videoRepository.save(savedVideo);
    return videoDto;
  }

  public String uploadThumbnail(MultipartFile file, String videoId)
  {
    Video savedVideo = getVideoById(videoId);
    String thumbnailUrl = s3Service.uploadFile(file);
    savedVideo.setThumbnail(thumbnailUrl);
    videoRepository.save(savedVideo);
    return thumbnailUrl;

  }

  private Video getVideoById(String videoId)
  {
    return videoRepository.findById(videoId).orElseThrow(()-> new IllegalArgumentException("Cannot find video by id " + videoId));
  }

}
