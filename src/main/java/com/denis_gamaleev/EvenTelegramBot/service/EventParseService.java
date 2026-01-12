package com.denis_gamaleev.EvenTelegramBot.service;

import com.denis_gamaleev.EvenTelegramBot.calendar_events.dto.CalendarEventRequest;

import java.time.ZoneId;

public interface EventParseService {

    CalendarEventRequest parse(String text, ZoneId userZone);
}
