package com.creatoriq.controllers;


import com.creatoriq.models.ai.VideoAnalysis;
import com.creatoriq.models.videos.VideoInfo;
import com.creatoriq.services.ai.VideoAnalysisService;
import com.creatoriq.services.videos.VideoIfoService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/chat", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChatController {


    private final ChatClient genAiChatClient;
    private final ChatClient groqChatClient;

    private final VideoIfoService videoIfoService;
    private final VideoAnalysisService videoAnalysisService;

    public ChatController(@Qualifier("genAiChatClient") ChatClient genAiChatClient, @Qualifier("groqChatClient") ChatClient groqChatClient, VideoIfoService videoIfoService, VideoAnalysisService videoAnalysisService) {
        this.genAiChatClient = genAiChatClient;
        this.groqChatClient = groqChatClient;
        this.videoIfoService = videoIfoService;
        this.videoAnalysisService = videoAnalysisService;
    }

    @GetMapping("/ask")
    public ResponseEntity<Flux<String>> ask(@RequestParam(value = "prompt") String prompt) {
        String googleResponse = genAiChatClient
                .prompt()
                .user(prompt)
                .system("You are a helpful assistant.")
                .call()
                .content();

        assert googleResponse != null;
        Flux<String> groqResponse = groqChatClient
                .prompt()
                .user(googleResponse)
                .system("Enhance the topic and make it more engaging and interesting.")
                .stream().content();

        return ResponseEntity.status(HttpStatus.OK).body(groqResponse);
    }

    @GetMapping(value = "/youtube", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<VideoInfo> youtubeVideoDetails(@RequestParam(value = "videoUrl") String videoUrl) {
        return videoIfoService.getVideoInfo(videoUrl);
    }


    @GetMapping(value = "/analyze", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<VideoAnalysis> analyzeVideo(@RequestParam(value = "videoUrl") String videoUrl) {
        return videoAnalysisService.analyzeVideo(videoUrl);
    }


}
