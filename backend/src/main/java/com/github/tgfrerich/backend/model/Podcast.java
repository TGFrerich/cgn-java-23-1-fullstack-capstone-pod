package com.github.tgfrerich.backend.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("podcasts")
public class Podcast {

    private String acoustic_model;
    private double audio_duration;
    private String audio_url;
    private double confidence;
    private Boolean dual_channel;
    private Boolean format_text;
    private String id;
    private String language_model;
    private Boolean punctuate;
    private String status;
    private String text;
    private List<Word> words;
    private Object utterances;
    private Integer webhook_status_code;
    private String webhook_url;


}
