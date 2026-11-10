package com.example.libraryapi;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GeminiService {

    private static final String URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent";

    private final Map<String, String> cache = new ConcurrentHashMap<>();

    public String summarize(String title, String author) {
        String cacheKey = title + "|" + author;
        String cached = cache.get(cacheKey);
        if (cached != null) return cached;
        String text = callGemini(title, author);
        cache.put(cacheKey, text);
        return text;
    }

    @SuppressWarnings("unchecked")
    private String callGemini(String title, String author) {
        String key = System.getenv("GEMINI_API_KEY");
        if (key == null || key.isBlank()) throw new IllegalStateException("GEMINI_API_KEY is not set");

        String prompt = "Write a short 3-sentence summary of the book \"" + title + "\" by " + author
                + ". Reply in the same language as the title.";
        Map<String, Object> body = Map.of("contents", List.of(Map.of("parts", List.of(Map.of("text", prompt)))));

        Map<String, Object> response = RestClient.create().post().uri(URL)
                .header("x-goog-api-key", key)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body).retrieve().body(Map.class);

        List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
        Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
        List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
        return (String) parts.get(0).get("text");
    }
}