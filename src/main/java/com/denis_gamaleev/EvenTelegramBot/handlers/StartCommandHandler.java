package com.denis_gamaleev.EvenTelegramBot.handlers;

import com.denis_gamaleev.EvenTelegramBot.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
@Order(1)
@RequiredArgsConstructor
public class StartCommandHandler implements CommandHandler {

    private final UserServiceImpl userService;

    @Override
    public boolean canHandle(String message) {
        return message.equalsIgnoreCase("/start");
    }

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.getMessage().getChatId();
        Long telegramId = update.getMessage().getFrom().getId();
        String userName =update.getMessage().getFrom().getUserName();

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        if (canHandle(update.getMessage().getText())) {
            if (!userService.isAuthorized(telegramId)) {
                userService.getOrCreateTelegramUser(telegramId, userName);
                message.setText("Этот бот позволяет создавать события в Яндекс календаре с помощью обычных сообщений\n" +
                        "Для доступа к его возможностям привяжите ваш Яндекс аккаунт к этому боту\n" +
                        "Для этого пришлите 'регистрация'");
            } else {
                message.setText("Этот бот позволяет создавать события в Яндекс календаре с помощью обычных сообщений\n" +
                        "Для доступа к его возможностям привяжите ваш Яндекс аккаунт к этому боту\n" +
                        "Для этого пришлите 'регистрация'");
            }
        }
        return message;
    }


}
