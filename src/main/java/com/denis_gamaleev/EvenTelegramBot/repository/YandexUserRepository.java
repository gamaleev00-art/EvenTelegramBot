package com.denis_gamaleev.EvenTelegramBot.repository;

import com.denis_gamaleev.EvenTelegramBot.entity.YandexUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YandexUserRepository extends JpaRepository<YandexUser,Long> {
}
