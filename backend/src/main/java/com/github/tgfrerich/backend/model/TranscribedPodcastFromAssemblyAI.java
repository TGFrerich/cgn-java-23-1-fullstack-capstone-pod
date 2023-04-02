package com.github.tgfrerich.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("podcasts")
public class TranscribedPodcastFromAssemblyAI {
    private String id;
    private String audio_url;
    private String status;
    private double audio_duration;
    private String acoustic_model;
    private String language_model;
    private String language_code;
    private Boolean format_text;
    private Boolean punctuate;
    private List<UtterancesFromSpeakersInTranscribedPodcast> utterances;
    private Boolean auto_chapters;
    private List<ChaptersInTranscribedPodcast> chapters;


}
