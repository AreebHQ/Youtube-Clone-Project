package com.youtube.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data //lombok getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
  @Id
  private String id;
  private String text;
  private String authorId;
  private Integer likeCount;
  private Integer dislikeCount;
}
