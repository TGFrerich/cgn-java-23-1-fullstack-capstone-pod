package com.github.tgfrerich.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssemblyAIApiResponse {

    String id;
    String status;
    String acoustic_model;
    int audio_duration;
    String audio_url;
    Boolean format_text;
    String language_model;
    String punctuate;
    String text;

}
