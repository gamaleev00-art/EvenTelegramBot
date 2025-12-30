package com.denis_gamaleev.EvenTelegramBot.service;

import com.denis_gamaleev.EvenTelegramBot.entity.TelegramUser;
import com.denis_gamaleev.EvenTelegramBot.entity.YandexUser;
import com.denis_gamaleev.EvenTelegramBot.repository.TelegramUserRepository;
import com.denis_gamaleev.EvenTelegramBot.repository.YandexUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final TelegramUserRepository telegramUserRepository;
    private final YandexUserRepository yandexUserRepository;

    @Override
    public void getOrCreateTelegramUser(Long telegramId, String username) {
        telegramUserRepository
                .findByTelegramId(telegramId)
                .orElseGet(()->{
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setTelegramId(telegramId);
                    telegramUser.setTelegramName(username);
                    telegramUserRepository.save(telegramUser);
                    return telegramUser;
                });
    }

    @Override
    public boolean isAuthorized(Long telegramId) {
        return telegramUserRepository.findByTelegramId(telegramId)
                .map(user->user.getYandexUser() != null)
                .orElse(false);
    }

    @Override
    public YandexUser getAuthorizedUser(Long telegramId) {
        TelegramUser user = telegramUserRepository
                .findByTelegramId(telegramId)
                .orElseThrow(()->new IllegalStateException("Telegram user not found"));
        if(user.getYandexUser()==null){
            throw new IllegalStateException("User not authorized in Yandex");
        }
        return user.getYandexUser();
    }

    @Override
    public void linkYandexAccount(Long telegramId, YandexUser yandexUser) {
        TelegramUser user =
                telegramUserRepository.findByTelegramId(telegramId)
                        .orElseThrow(()->new IllegalStateException("Telegram user not found"));

        user.setYandexUser(yandexUser);
        yandexUser.setTelegramUser(user);
        telegramUserRepository.save(user);
        yandexUserRepository.save(yandexUser);
    }

    @Override
    public Optional<TelegramUser> getTelegramUserById(Long telegramId) {
        return telegramUserRepository.findByTelegramId(telegramId);
    }
}
