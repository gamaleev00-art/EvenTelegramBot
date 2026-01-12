package com.denis_gamaleev.EvenTelegramBot.handlers;

import com.denis_gamaleev.EvenTelegramBot.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;


@Component
@Order(3)
@RequiredArgsConstructor
public class HelpCommandHandler implements CommandHandler {

    private final UserServiceImpl userService;


    @Override
    public boolean canHandle(String message) {
        return message.equalsIgnoreCase("покажи что умеешь")
                || message.equalsIgnoreCase("покажи что ты умеешь")
                || message.equalsIgnoreCase("что ты умеешь");
    }


    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.getMessage().getChatId();
        Long telegramId = update.getMessage().getFrom().getId();

        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        if (!userService.isAuthorized(telegramId)) {
                message.setText("""
                Я умею:
                • создавать события из обычного текста
                • показывать запланированные дела
                • работать с Яндекс Календарем
                """);
        } else {
            message.setText("""
                Я умею:
                • создавать события из обычного текста
                • показывать запланированные дела
                • работать с Яндекс Календарем
                Но для этого необходимо зарегистрироваться
                """);
        }
        return message;
    }
}
