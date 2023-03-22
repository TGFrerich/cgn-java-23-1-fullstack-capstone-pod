package com.github.tgfrerich.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("podcasts")
public class Podcast {

    private String id;
    private String acoustic_model;
    private double audio_duration;
    private String audio_url;
    private double confidence;
    private Boolean format_text;

    private String language_model;
    private Boolean punctuate;
    private String status;
    private String text;


}
