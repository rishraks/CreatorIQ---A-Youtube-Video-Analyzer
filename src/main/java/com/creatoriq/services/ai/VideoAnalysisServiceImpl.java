package com.creatoriq.services.ai;

import com.creatoriq.models.ai.VideoAnalysis;
import com.creatoriq.models.videos.VideoInfo;
import com.creatoriq.services.videos.VideoInfoServiceImpl;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class VideoAnalysisServiceImpl implements VideoAnalysisService {


    private final ChatClient groqChatClient;
    private final ChatClient genAiChatClient;
    private final VideoInfoServiceImpl videoIfoService;
    private final PromptTemplate promptTemplate;

    public VideoAnalysisServiceImpl(@Qualifier("groqChatClient") ChatClient groqChatClient, @Qualifier("genAiChatClient") ChatClient genAiChatClient, VideoInfoServiceImpl videoIfoService, PromptTemplate promptTemplate) {
        this.groqChatClient = groqChatClient;
        this.genAiChatClient = genAiChatClient;
        this.videoIfoService = videoIfoService;
        this.promptTemplate = new PromptTemplate(
                new ClassPathResource("prompts/video-analyzer.st")
        );
    }

    @Override
    public Mono<VideoAnalysis> analyzeVideo(String videoUrl) {
        return videoIfoService.getVideoInfo(videoUrl)
                .flatMap(videoInfo -> {
                    Map<String, Object> variables = createVariables(videoInfo);
                    Prompt prompt = promptTemplate.create(variables);

                    return Mono.fromCallable(() -> {
                        var response = genAiChatClient
                                .prompt(prompt)
                                .call();  // this returns AiResponse immediately (NOT mono)

                        VideoAnalysis va = response.entity(VideoAnalysis.class);
                        assert va != null;
                        va.setAnalyzedDate(LocalDateTime.now());
                        va.setModelUsed("Google Gemini-2.5-flash");

                        return va;
                    });
                });
    }

    private Map<String, Object> createVariables(VideoInfo videoInfo) {
        return Map.ofEntries(
                Map.entry("videoId", videoInfo.getVideoId()),
                Map.entry("title", videoInfo.getTitle()),
                Map.entry("channelId", videoInfo.getChannelId()),
                Map.entry("description", videoInfo.getDescription()),
                Map.entry("duration", videoInfo.getDuration()),
                Map.entry("tags", String.join(", ", videoInfo.getTags())),
                Map.entry("viewCount", videoInfo.getViewCount()),
                Map.entry("likeCount", videoInfo.getLikeCount()),
                Map.entry("commentCount", videoInfo.getCommentCount()),
                Map.entry("licensedContent", videoInfo.isLicensedContent()),
                Map.entry("thumbnailUrl", videoInfo.getThumbnailUrl())
        );
    }
}
