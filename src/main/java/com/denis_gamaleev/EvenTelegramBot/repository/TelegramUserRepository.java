package com.denis_gamaleev.EvenTelegramBot.repository;

import com.denis_gamaleev.EvenTelegramBot.entity.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
    Optional<TelegramUser> findByTelegramId(Long telegramId);
}
