package com.github.tgfrerich.backend.service;

import com.github.tgfrerich.backend.model.RequestBodyForAssemblyAI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URL;

@Service
public class PodService {


    public RequestBodyForAssemblyAI sendUrl(String url) {
        if (isValidURL(url)) {
            RequestBodyForAssemblyAI requestBodyForAssemblyAI = new RequestBodyForAssemblyAI(url);
            return requestBodyForAssemblyAI;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    public boolean isValidURL(String urlString) {
        try {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
