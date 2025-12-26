package com.denis_gamaleev.EvenTelegramBot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TelegramUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long telegramId;

    private String telegramName;

    private String timezone;

    @OneToOne(mappedBy = "telegramUser")
    private YandexUser yandexUser;
}
