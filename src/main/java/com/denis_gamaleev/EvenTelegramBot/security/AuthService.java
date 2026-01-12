package com.denis_gamaleev.EvenTelegramBot.security;

public interface AuthService {
    String getAuthorizationLink(Long telegramUserId);

    void handleCallback(String code, Long telegramUserId);

    boolean isYandexLinked(Long telegramUserId);
}
