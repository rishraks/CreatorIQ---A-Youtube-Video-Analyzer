package com.creatoriq.services.videos;

import com.creatoriq.models.videos.VideoInfo;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VideoInfoServiceImpl implements VideoIfoService {

    private final WebClient webClient;
    private final String VIDEO_FIELDS =
            "items(id, snippet(channelId,title,description,tags,thumbnails), contentDetails(duration,licensedContent), statistics(viewCount,likeCount,commentCount))";
    private final String PART = "snippet,contentDetails,statistics";

    @Value("${spring.app.youtube.api-key}")
    private String API_KEY;

    public VideoInfoServiceImpl(@Qualifier("youtubeWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<VideoInfo> getVideoInfo(String videoUrl) {
        String videoId = getVideoId(videoUrl);
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("id", videoId)
                            .queryParam("key", API_KEY)
                            .queryParam("fields", VIDEO_FIELDS)
                            .queryParam("part", PART)
                            .build())
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .map(this::mapJsonToVideoInfo)
                    .onErrorResume(e -> Mono.empty())
                    .retry();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private VideoInfo mapJsonToVideoInfo(JsonNode jsonNode) {

        JsonNode json = jsonNode.path("items").get(0);
        VideoInfo videoInfo = new VideoInfo();

        videoInfo.setVideoId(json.path("id").asText());

        JsonNode snippet = json.path("snippet");

        // Snippet
        videoInfo.setChannelId(snippet.path("channelId").asText());
        videoInfo.setTitle(snippet.path("title").asText());
        videoInfo.setDescription(snippet.path("description").asText());
        JsonNode tags = snippet.path("tags");
        List<String> tagsList = new ArrayList<>();
        if (tags.isArray()) {
            for (JsonNode tag : tags) {
                tagsList.add(tag.asText());
            }
        }
        videoInfo.setTags(tagsList);
        videoInfo.setThumbnailUrl(snippet.path("thumbnails").path("default").path("url").asText());

        // ContentDetails
        JsonNode contentDetails = json.path("contentDetails");
        videoInfo.setDuration(contentDetails.path("duration").asText());
        videoInfo.setLicensedContent(contentDetails.path("licensedContent").asBoolean());

        // Statistics
        JsonNode statistics = json.path("statistics");
        videoInfo.setViewCount(statistics.path("viewCount").asText());
        videoInfo.setLikeCount(statistics.path("likeCount").asText());
        videoInfo.setCommentCount(statistics.path("commentCount").asText());

        return videoInfo;
    }

    private String getVideoId(String videoUrl) {
        String videoId = "";
        String regex = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?(?:feature=youtu.be\\&)?v=|v\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(videoUrl);
        if (matcher.find()) {
            videoId = matcher.group(1);
        }
        return videoId;
    }

}
