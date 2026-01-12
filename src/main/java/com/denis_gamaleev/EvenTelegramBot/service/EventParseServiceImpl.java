package com.denis_gamaleev.EvenTelegramBot.service;

import com.denis_gamaleev.EvenTelegramBot.calendar_events.CalendarEventRequestBuilder;
import com.denis_gamaleev.EvenTelegramBot.calendar_events.dto.CalendarEventRequest;
import com.denis_gamaleev.EvenTelegramBot.parsers.DateTimeParser;
import com.denis_gamaleev.EvenTelegramBot.parsers.TitleParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class EventParseServiceImpl implements EventParseService {

    private final DateTimeParser dateTimeParser;
    private final TitleParser titleParser;

    @Override
    public CalendarEventRequest parse(String text, ZoneId userZone) {

        LocalDateTime start = dateTimeParser.parse(text, userZone);
        LocalDateTime end = start.plusHours(1);

        String title = titleParser.parse(text);

        return CalendarEventRequestBuilder.build(title, start, end, userZone);
    }
}
