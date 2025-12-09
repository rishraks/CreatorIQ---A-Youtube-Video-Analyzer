package com.creatoriq.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ChatClientConfig {

    @Bean(name = "groqChatClient")
    public ChatClient groqChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel).build();
    }

    @Bean(name = "genAiChatClient")
    public ChatClient genAiChatClient(GoogleGenAiChatModel googleGenAiChatModel) {
        return ChatClient.builder(googleGenAiChatModel).build();
    }

    @Bean(name = "youtubeWebClient")
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/videos")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return Jackson2ObjectMapperBuilder.json();
    }

    @Bean
    public PromptTemplate videoAnalysisPromptTemplate() {
        return new PromptTemplate(new ClassPathResource("prompts/video-analyzer.st"));
    }

}
