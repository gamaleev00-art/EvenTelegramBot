package com.denis_gamaleev.EvenTelegramBot.service;

import com.denis_gamaleev.EvenTelegramBot.config.TelegramBotConfig;
import com.denis_gamaleev.EvenTelegramBot.handlers.CommandHandler;
import com.denis_gamaleev.EvenTelegramBot.handlers.dispatcher.CommandDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TelegramBotService extends TelegramLongPollingBot {


    private final CommandDispatcher dispatcher;
    private final TelegramBotConfig config;


    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
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

        SendMessage response = dispatcher.dispatch(update);

        try {
            execute(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
