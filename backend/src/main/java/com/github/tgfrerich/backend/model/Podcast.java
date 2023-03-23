package com.github.tgfrerich.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document("podcasts")
public class Podcast {
    private String internalId;
    private String id;
    private String acousticModel;
    private double audioDuration;
    private String audioUrl;
    private String languageCode;
    private double confidence;
    private Boolean formatText;

    private String languageModel;
    private Boolean punctuate;
    private String status;
    private String text;


}
