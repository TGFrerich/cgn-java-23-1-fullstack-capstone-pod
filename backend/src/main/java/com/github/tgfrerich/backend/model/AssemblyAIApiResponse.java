package com.github.tgfrerich.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssemblyAIApiResponse {

    String id;
    String status;
    String acoustic_model;
    int audio_duration;
    String audio_url;
    Boolean format_text;
    String language_model;
    Boolean punctuate;
    String text;

    Boolean speaker_labels;
    Boolean auto_chapters;


}
