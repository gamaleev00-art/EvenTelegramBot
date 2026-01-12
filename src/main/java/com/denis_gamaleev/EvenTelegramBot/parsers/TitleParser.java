package com.denis_gamaleev.EvenTelegramBot.parsers;

import org.springframework.stereotype.Component;

@Component
public class TitleParser {

    public String parse(String text) {
        return text
                .replaceAll("сегодня|завтра|послезавтра|в|пол|\\d{1,2}:?\\d{0,2}", "")
                .trim();
    }
}
