package com.denis_gamaleev.EvenTelegramBot.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class YandexOAuthClient implements OAuthClient{
    private final YandexOAuthProperties properties;
    private final RestTemplate plainRestTemplate;

    @Override
    public String buildAuthUrl(Long telegramUserId) {
        return properties.getAuthUrl()
                + "?response_type=code"
                + "&client_id=" + properties.getClientId()
                + "&redirect_uri=" + properties.getRedirectUri()
                + "&scope=calendar"
                + "&state=" + telegramUserId;
    }

    @Override
    public OAuthTokenResponse exchangeCodeForToken(String code) {
        return plainRestTemplate.postForObject(
                properties.getTokenUrl(),
                buildTokenRequest(code),
                OAuthTokenResponse.class
        );
    }

    @Override
    public OAuthTokenResponse refreshToken(String refreshToken) {
        return plainRestTemplate.postForObject(
                properties.getTokenUrl(),
                buildRefreshRequest(refreshToken),
                OAuthTokenResponse.class
        );
    }

    private HttpEntity<MultiValueMap<String, String>> buildTokenRequest(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", code);
        body.add("client_id", properties.getClientId());
        body.add("client_secret", properties.getClientSecret());
        body.add("redirect_uri", properties.getRedirectUri());

        return new HttpEntity<>(body, headers);
    }

    private HttpEntity<MultiValueMap<String, String>> buildRefreshRequest(String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", refreshToken);
        body.add("client_id", properties.getClientId());
        body.add("client_secret", properties.getClientSecret());

        return new HttpEntity<>(body, headers);
    }
}
