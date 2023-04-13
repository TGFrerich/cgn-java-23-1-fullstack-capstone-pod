package com.github.tgfrerich.backend.configuration;

import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.time.Duration;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient customWebClient() {
        int maxBufferSizeInBytes = 10 * 1024 * 1024;

        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMinutes(1))
                .secure(sslContextSpec -> {
                    try {
                        sslContextSpec.sslContext(sslContextBuilderForTesting().build());
                    } catch (SSLException e) {
                        throw new IllegalStateException("Error configuring SSL context", e);
                    }
                });
        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        return WebClient.builder()
                .baseUrl("https://api.assemblyai.com/v2")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + System.getenv("API_KEY_ASSEMBLYAI"))
                .clientConnector(connector)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(maxBufferSizeInBytes))
                .build();
    }

    protected SslContextBuilder sslContextBuilderForTesting() throws SSLException {
        return SslContextBuilder.forClient();
    }

}

