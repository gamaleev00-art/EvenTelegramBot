package com.denis_gamaleev.EvenTelegramBot.handlers.dispatcher;

import com.denis_gamaleev.EvenTelegramBot.handlers.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandDispatcher {

    private final List<CommandHandler> handlers;

    public SendMessage dispatch(Update update) {

        String text = update.getMessage().getText();

        for (CommandHandler handler : handlers) {
            if (handler.canHandle(text)) {
                return handler.handle(update);
            }
        }

        return new SendMessage(
                update.getMessage().getChatId().toString(),
                "Не понял команду"
        );
    }
}
