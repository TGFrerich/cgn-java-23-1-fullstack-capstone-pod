package com.github.tgfrerich.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PodService {


    public String sendUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return url;
        }

    }
}
