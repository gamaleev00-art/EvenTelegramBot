package com.denis_gamaleev.EvenTelegramBot.handlers;

import com.denis_gamaleev.EvenTelegramBot.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;


@Component
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
                message.setText("Я бот который преобразует обычные сообщения в события в Яндекс календаре и проверяет список имеющихся планов\n" +
                        "Хотите записать новое событие?\n" +
                        "Тогда пришли сообщение любого формата по типу:\n" +
                        "завтра в 19:00 тренировка\n" +
                        "25 мая в 18 встреча\n" +
                        "покажи мои дела\n");
        } else {
            message.setText("Я бот который преобразует обычные сообщения в события в Яндекс календаре и проверяет список имеющихся планов\n" +
                    "Для использования полного функционала зарегистрируйтесь как Яндекс пользователь\n" +
                    "Для этого пришлите 'регистрация'");
        }
        return message;
    }
}
