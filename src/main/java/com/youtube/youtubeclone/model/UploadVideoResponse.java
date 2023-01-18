package com.youtube.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadVideoResponse {

  private String videoId;
  private String videoUrl;

}
