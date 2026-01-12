package com.denis_gamaleev.EvenTelegramBot.security;

public interface OAuthClient {
    String buildAuthUrl(Long telegramUserId);

    OAuthTokenResponse exchangeCodeForToken(String code);

    OAuthTokenResponse refreshToken(String refreshToken);
}
