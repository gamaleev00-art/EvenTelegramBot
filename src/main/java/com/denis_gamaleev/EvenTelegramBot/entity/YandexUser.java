package com.denis_gamaleev.EvenTelegramBot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class YandexUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String accessToken;
    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "telegram_user_id")
    private TelegramUser telegramUser;
}
