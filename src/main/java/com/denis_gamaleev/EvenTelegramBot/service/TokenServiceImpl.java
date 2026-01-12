package com.denis_gamaleev.EvenTelegramBot.service;

import com.denis_gamaleev.EvenTelegramBot.entity.YandexUser;
import com.denis_gamaleev.EvenTelegramBot.repository.YandexUserRepository;
import com.denis_gamaleev.EvenTelegramBot.security.OAuthClient;
import com.denis_gamaleev.EvenTelegramBot.security.OAuthTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final YandexUserRepository yandexUserRepository;
    private final OAuthClient oAuthClient;

    @Override
    public String getValidAccessToken(Long telegramUserId) {

        YandexUser user = yandexUserRepository
                .findByTelegramUser_TelegramId(telegramUserId)
                .orElseThrow(() -> new IllegalStateException("Yandex account not linked"));

        if (user.getExpiresAt().isBefore(Instant.now())) {
            OAuthTokenResponse refreshed =
                    oAuthClient.refreshToken(user.getRefreshToken());

            user.setAccessToken(refreshed.getAccess_token());
            user.setRefreshToken(refreshed.getRefresh_token());
            user.setExpiresAt(
                    Instant.now().plusSeconds(refreshed.getExpires_in())
            );

            yandexUserRepository.save(user);
        }

        return user.getAccessToken();
    }
}
