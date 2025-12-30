package com.denis_gamaleev.EvenTelegramBot.service;

import com.denis_gamaleev.EvenTelegramBot.config.TelegramBotConfig;
import com.denis_gamaleev.EvenTelegramBot.handlers.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TelegramBotService extends TelegramLongPollingBot {


    private List<CommandHandler> handlers;
    private UserServiceImpl userServiceImpl;
    private TelegramBotConfig telegramBotConfig;

    @Override
    public String getBotToken() {
        return telegramBotConfig.getToken();
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getName();
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        String text = update.getMessage().getText();

        handlers.stream()
                .filter(handler -> handler.canHandle(text))
                .findFirst()
                .ifPresent(handler -> {
                    SendMessage response = handler.handle(update);
                    try {
                        execute(response);
                    } catch (Exception e) {
                        // логирование
                    }
                });
    }
}
