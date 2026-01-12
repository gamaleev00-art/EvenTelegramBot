package com.denis_gamaleev.EvenTelegramBot.parsers;

import java.time.LocalDateTime;
import java.time.ZoneId;

public interface DateTimeParser {

    LocalDateTime parse(String text, ZoneId zoneId);
}
