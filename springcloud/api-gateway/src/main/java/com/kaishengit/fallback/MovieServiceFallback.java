package com.kaishengit.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class MovieServiceFallback implements ZuulFallbackProvider {
    /**
     * 容错的服务名称（service-id），*表示所有服务
     *
     * @return The route the fallback will be used for.
     */
    @Override
    public String getRoute() {
        return "*";
    }

    /**
     * Provides a fallback response.
     *
     * @return The fallback response.
     */
    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("Sorry,API暂时不可用".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.parseMediaType("text/html;charset=UTF-8"));
                return httpHeaders;
            }
        };
    }
}
