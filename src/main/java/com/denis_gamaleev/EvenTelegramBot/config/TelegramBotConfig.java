package com.denis_gamaleev.EvenTelegramBot.config;


import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;



@Configuration
@Getter
public class TelegramBotConfig {
    @Value("${bot.token}")
    private String token;
    @Value("${bot.name}")
    private String name;
}
