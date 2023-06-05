import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadVideoResponse } from './upload-video/UploadVideoResponse';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }

  uploadVideo(fileEntry: File): Observable<UploadVideoResponse>{

    const formData = new FormData();
    formData.append('file', fileEntry, fileEntry.name);

   return this.httpClient.post<UploadVideoResponse>("http://localhost:8080/api/v1/upload",formData);
  }

  uploadThumbnail(fileEntry: File, videoId: string): Observable<string> {

    const formData = new FormData();
    formData.append('file', fileEntry, fileEntry.name);
    formData.append('videoId',videoId);

   return this.httpClient.post("http://localhost:8080/api/v1/videos/thumbnail",formData,{
     responseType: 'text'
   });
  }
}
