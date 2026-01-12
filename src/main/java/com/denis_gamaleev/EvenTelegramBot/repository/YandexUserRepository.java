package com.denis_gamaleev.EvenTelegramBot.repository;

import com.denis_gamaleev.EvenTelegramBot.entity.YandexUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface YandexUserRepository extends JpaRepository<YandexUser,Long> {
    boolean existsByTelegramUser_TelegramId(Long telegramId);

    Optional<YandexUser> findByTelegramUser_TelegramId(Long telegramId);
}
