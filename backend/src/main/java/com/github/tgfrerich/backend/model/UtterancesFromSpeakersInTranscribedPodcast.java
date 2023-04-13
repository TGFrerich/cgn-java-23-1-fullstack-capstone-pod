package com.github.tgfrerich.backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UtterancesFromSpeakersInTranscribedPodcast {

    private String speaker;
    private String text;

    private Integer start;
    private Integer end;

}
