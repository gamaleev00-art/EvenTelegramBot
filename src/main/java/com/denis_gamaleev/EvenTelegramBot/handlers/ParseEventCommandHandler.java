package com.denis_gamaleev.EvenTelegramBot.handlers;

import com.denis_gamaleev.EvenTelegramBot.calendar_events.dto.CalendarEventRequest;
import com.denis_gamaleev.EvenTelegramBot.security.AuthService;
import com.denis_gamaleev.EvenTelegramBot.service.EventParseService;
import com.denis_gamaleev.EvenTelegramBot.service.EventParseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.ZoneId;

@Component
@Order(100)
@RequiredArgsConstructor
public class ParseEventCommandHandler implements CommandHandler {

    private final EventParseService eventParseService;
   // private final CalendarService calendarService;
    private final AuthService authService;

    @Override
    public boolean canHandle(String message) {
        return true;
    }

    @Override
    public SendMessage handle(Update update) {

        Long telegramUserId = update.getMessage().getFrom().getId();

        if (!authService.isYandexLinked(telegramUserId)) {
            return new SendMessage(
                    update.getMessage().getChatId().toString(),
                    "Сначала привяжите Яндекс аккаунт"
            );
        }

        try {
            CalendarEventRequest request =
                    eventParseService.parse(
                            update.getMessage().getText(),
                            ZoneId.systemDefault()
                    );

            //calendarService.createEvent(telegramUserId, request);

            return new SendMessage(
                    update.getMessage().getChatId().toString(),
                    "Событие успешно добавлено"
            );

        } catch (Exception e) {
            return new SendMessage(
                    update.getMessage().getChatId().toString(),
                    "Не смог распознать дату"
            );
        }
    }
}
