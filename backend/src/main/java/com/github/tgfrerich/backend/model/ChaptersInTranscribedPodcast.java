package com.github.tgfrerich.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaptersInTranscribedPodcast {
    private Integer start;
    private Integer end;
    private String summary;
    private String headline;
    private String gist;
}
