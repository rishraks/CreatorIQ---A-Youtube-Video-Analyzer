package com.creatoriq.services.videos;

import com.creatoriq.models.videos.VideoInfo;
import reactor.core.publisher.Mono;

public interface VideoIfoService {
    Mono<VideoInfo> getVideoInfo(String videoUrl);
}
