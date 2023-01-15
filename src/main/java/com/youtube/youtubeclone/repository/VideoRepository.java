package com.youtube.youtubeclone.repository;


import com.youtube.youtubeclone.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository <Video,String>{
}
