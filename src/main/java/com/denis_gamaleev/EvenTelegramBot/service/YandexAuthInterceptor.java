package com.denis_gamaleev.EvenTelegramBot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class YandexAuthInterceptor implements ClientHttpRequestInterceptor {

    private final TokenService tokenService;

    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        Long telegramUserId = extractTelegramUserId(request);

        String token = tokenService.getValidAccessToken(telegramUserId);

        request.getHeaders().setBearerAuth(token);

        return execution.execute(request, body);
    }

    private Long extractTelegramUserId(HttpRequest request) {
        return Long.parseLong(
                request.getHeaders().getFirst("X-Telegram-User-Id")
        );
    }
}
