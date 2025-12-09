package com.creatoriq.services.ai;

import com.creatoriq.models.ai.VideoAnalysis;
import reactor.core.publisher.Mono;

public interface VideoAnalysisService {
    Mono<VideoAnalysis> analyzeVideo(String videoUrl);
}
