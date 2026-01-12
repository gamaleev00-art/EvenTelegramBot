package com.denis_gamaleev.EvenTelegramBot.security;

import com.denis_gamaleev.EvenTelegramBot.entity.TelegramUser;
import com.denis_gamaleev.EvenTelegramBot.entity.YandexUser;
import com.denis_gamaleev.EvenTelegramBot.repository.TelegramUserRepository;
import com.denis_gamaleev.EvenTelegramBot.repository.YandexUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final OAuthClient yandexOAuthClient;
    private final YandexUserRepository yandexUserRepository;
    private final TelegramUserRepository telegramUserRepository;

    @Override
    public String getAuthorizationLink(Long telegramUserId) {
        return yandexOAuthClient.buildAuthUrl(telegramUserId);
    }

    @Override
    public void handleCallback(String code, Long telegramUserId) {
        OAuthTokenResponse tokens = yandexOAuthClient.exchangeCodeForToken(code);

        TelegramUser telegramUser =
                telegramUserRepository.findByTelegramId(telegramUserId)
                        .orElseThrow();

        YandexUser yandexUser = YandexUser.from(tokens, telegramUser);
        yandexUserRepository.save(yandexUser);
    }

    @Override
    public boolean isYandexLinked(Long telegramUserId) {
        return yandexUserRepository.existsByTelegramUser_TelegramId(telegramUserId);
    }
}
