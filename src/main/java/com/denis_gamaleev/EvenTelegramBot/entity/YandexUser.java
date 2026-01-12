package com.denis_gamaleev.EvenTelegramBot.entity;

import com.denis_gamaleev.EvenTelegramBot.security.OAuthTokenResponse;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class YandexUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String accessToken;
    private String refreshToken;
    private Instant expiresAt;

    @OneToOne
    @JoinColumn(name = "telegram_user_id")
    private TelegramUser telegramUser;

    public static YandexUser from(OAuthTokenResponse tokens, TelegramUser telegramUser) {
        YandexUser user = new YandexUser();
        user.accessToken = tokens.getAccess_token();
        user.refreshToken = tokens.getRefresh_token();
        user.telegramUser = telegramUser;
        user.setExpiresAt(Instant.now());
        return user;
    }
}
