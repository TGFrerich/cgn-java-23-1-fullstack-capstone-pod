package com.github.tgfrerich.backend.configuration;


import io.netty.handler.ssl.SslContextBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.SSLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WebClientConfigurationTest {

    private WebClientConfiguration webClientConfiguration;

    @BeforeEach
    public void setUp() {
        webClientConfiguration = new WebClientConfiguration();
    }

    @Test
    public void customWebClientTest() {
        int expectedMaxBufferSizeInBytes = 10 * 1024 * 1024;

        WebClient webClient = webClientConfiguration.customWebClient();

        assertNotNull(webClient, "WebClient should not be null");
    }

    @Test
    public void customWebClientSslExceptionTest() {
        WebClientConfiguration testableWebClientConfiguration = new WebClientConfiguration() {
            @Override
            protected SslContextBuilder sslContextBuilderForTesting() throws SSLException {
                throw new SSLException("Test SSLException");
            }
        };

        assertThrows(IllegalStateException.class, testableWebClientConfiguration::customWebClient, "Expected SslConfigurationException");
    }
}
