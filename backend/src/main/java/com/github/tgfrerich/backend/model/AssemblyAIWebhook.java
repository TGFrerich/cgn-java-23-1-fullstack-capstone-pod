package com.github.tgfrerich.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssemblyAIWebhook {

    private String id;
    private String status;

}
