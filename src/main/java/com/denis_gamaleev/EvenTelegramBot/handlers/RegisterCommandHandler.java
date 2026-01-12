package com.denis_gamaleev.EvenTelegramBot.handlers;


import com.denis_gamaleev.EvenTelegramBot.security.AuthService;
import com.denis_gamaleev.EvenTelegramBot.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;



@Component
@Order(1)
@RequiredArgsConstructor
public class RegisterCommandHandler implements CommandHandler {

   private final AuthService authService;


    @Override
    public boolean canHandle(String message) {
        return message.equalsIgnoreCase("регистрация");
    }

    @Override
    public SendMessage handle(Update update) {

        String link = authService.getAuthorizationLink(update.getMessage().getFrom().getId());

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText("Перейдите по ссылке:\n" + link);

        return message;
    }

}
