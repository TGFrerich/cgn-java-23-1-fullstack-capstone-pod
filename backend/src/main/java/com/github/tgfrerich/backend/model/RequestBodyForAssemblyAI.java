package com.github.tgfrerich.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBodyForAssemblyAI {

    String id;
    String audio_url;
    Boolean auto_chapters;
    Boolean speaker_labels;
}
