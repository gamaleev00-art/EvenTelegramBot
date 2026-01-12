package com.denis_gamaleev.EvenTelegramBot.service;

public interface TokenService {

    String getValidAccessToken(Long telegramUserId);
}
