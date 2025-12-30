package com.denis_gamaleev.EvenTelegramBot.service;

import com.denis_gamaleev.EvenTelegramBot.entity.TelegramUser;
import com.denis_gamaleev.EvenTelegramBot.entity.YandexUser;

import java.util.Optional;

public interface UserService {
    void getOrCreateTelegramUser(Long telegramId, String username);
    boolean isAuthorized(Long telegramId);
    YandexUser getAuthorizedUser(Long telegramId);
    void linkYandexAccount (Long telegramId, YandexUser user);
    Optional<TelegramUser> getTelegramUserById(Long telegramId);
}
