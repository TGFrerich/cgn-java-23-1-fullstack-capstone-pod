package com.github.tgfrerich.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBodyForAssemblyAI {
    @JsonProperty("audio_url")
    private String audio_url;
    @JsonProperty("webhook_url")
    private String webhook_url;
    @JsonProperty("auto_chapters")
    private boolean auto_chapters;

    @JsonProperty("speaker_labels")
    private boolean speaker_labels;


    public RequestBodyForAssemblyAI(String audio_url, String webhook_url) {
        this.audio_url = audio_url;
        this.auto_chapters = true;
        this.speaker_labels = true;
        this.webhook_url = webhook_url;
    }
}
