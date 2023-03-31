package com.github.tgfrerich.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestBodyForAssemblyAI {

    String id;
    String audio_url;
}
