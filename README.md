# CreatorIQ---A-Youtube-Video-Analyzer
A Spring AI project primarily focused on analyzing Youtube Videos. The AI analyzes the video on multiple parameters and provides meaningful insights to enhance the quality and boost the engagement of the video.

## ✨ Features

- **📺 YouTube API Integration**: Fetch video metadata, statistics, and thumbnails
- **🤖 Multi-AI Analysis**: Choose between Google Gemini or Groq models
- **⚡ Reactive Architecture**: Built with Spring WebFlux for high performance
- **📊 Structured Insights**: AI provides SEO, engagement, and content quality analysis
- **🔧 Professional REST API**: Clean endpoints with proper error handling
- **🎯 Actionable Recommendations**: Specific improvements and topic suggestions

## 🚀 Quick Start

### Prerequisites
- Java 21 or higher
- Maven 3.6+
- YouTube Data API v3 key
- Gemini API key (or Groq API key)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/creatoriq.git
cd creatoriq
```
2. Configure API Keys
Set the following environment variables:
```bash
export YOUTUBE_API_KEY=your_youtube_key
export GEMINI_API_KEY=your_gemini_key
export GROQ_API_KEY=your_groq_key  # Optional
```
3. Build and Run
```bash
./mvnw clean package
java -jar target/creatoriq.jar
```
4. Access the API
```bash
http://localhost:8082
```

### 📡 API Reference
1. Get YouTube Video Metadata
```bash
GET /chat/youtube?videoUrl={youtube_url}
```

- Fetches raw video data from YouTube API including title, description, statistics, and thumbnails.
### Parameters:
* videoUrl (required): Any YouTube URL format
  * https://youtube.com/watch?v=VIDEO_ID
  * https://youtu.be/VIDEO_ID
  * https://youtube.com/shorts/VIDEO_ID
  
### Example Request:
```bash
curl "http://localhost:8082/chat/youtube?videoUrl=https://youtu.be/AZxD0qmqxtM"
```

### Exxample Response:
```bash
{
  "videoId": "AZxD0qmqxtM",
  "channelId": "UCfeAXn24w1WXNsPHkqjvVxg",
  "title": "Why You Should Be Using MainStage",
  "description": "MainStage Tips Playlist...",
  "tags": ["mainstage"],
  "thumbnailUrl": "https://i.ytimg.com/vi/AZxD0qmqxtM/default.jpg",
  "duration": "PT9M24S",
  "licensedContent": true,
  "viewCount": "49950",
  "likeCount": "1140",
  "commentCount": "69"
}
```
2. Get AI-Powered Video Analysis
```bash
GET /chat/analyze?videoUrl={youtube_url}
```
Analyzes the video using AI to provide content strategy insights, SEO recommendations, and engagement analysis.

### Parameters:
* videoUrl (required): YouTube URL to analyze

### Example Request:
```bash
curl "http://localhost:8082/chat/analyze?videoUrl=https://youtu.be/AZxD0qmqxtM"
```

### Example Response:
```bash
{
  "summary": "This video serves as a comprehensive introduction and persuasive argument for using Apple's MainStage software...",
  "contentQuality": "Good",
  "engagementScore": 7,
  "strengths": [
    "Clear and logical video structure with well-defined chapters...",
    "Comprehensive explanation of MainStage's features, benefits, and differentiators..."
  ],
  "improvements": [
    "Expand the tag list significantly to include more relevant keywords...",
    "Incorporate more dynamic visuals or screen-share demonstrations..."
  ],
  "suggestedTopics": [
    "MainStage vs. Ableton Live: A Live Performance Software Comparison",
    "Building Your First MainStage Concert: A Step-by-Step Guide for Beginners"
  ],
  "seoAnalysis": "The title 'Why You Should Be Using MainStage' is excellent; it's clear, benefit-oriented, and includes the primary keyword...",
  "thumbnailSuggestions": "A visually dynamic thumbnail featuring a split image...",
  "targetedAudience": "Keyboardists, musicians, audio engineers, and sound designers involved in live performance...",
  "analyzedDate": [2025, 12, 9, 12, 45, 57, 936600000],
  "modelUsed": "Google Gemini-2.5-flash"
}
```

## Architecture:
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Client        │    │   Spring Boot   │    │   External      │
│   (Frontend)    │───▶│   Backend       │───▶│   APIs          │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                        │                       │
         │ 1. /chat/youtube       │ 2. YouTube Data API   │
         │    (video metadata)    │    (video data)       │
         │◀───────────────────────│◀──────────────────────│
         │                        │                       │
         │ 3. /chat/analyze       │ 4. AI Model           │
         │    (AI analysis)       │    (Gemini/Groq)      │
         │◀───────────────────────│◀──────────────────────│


## Technology Stack:
Component	          Technology	               Purpose
Framework	          Spring Boot 3.2	           Application framework
Web Layer	          Spring WebFlux	           Reactive REST API
AI Integration	    Spring AI 1.1	             Multi-model AI support
AI Models	          Google Gemini 2.5, Groq	   Video analysis
External API	      YouTube Data API v3	       Video metadata
Language	          Java 21	                   Backend logic
Build Tool	        Maven	                     Dependency management
JSON Processing	    Jackson	                   Serialization/deserialization
Lombok	            Lombok	                   Boilerplate reduction

### License
This project is licensed under the MIT License - see the LICENSE file for details.
