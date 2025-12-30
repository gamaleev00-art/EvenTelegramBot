package com.denis_gamaleev.EvenTelegramBot.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandHandler {
    boolean canHandle(String message);
    SendMessage handle(Update update);
}
