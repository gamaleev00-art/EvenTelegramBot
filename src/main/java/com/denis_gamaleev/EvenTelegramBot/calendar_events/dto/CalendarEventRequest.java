package com.denis_gamaleev.EvenTelegramBot.calendar_events.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarEventRequest {

    private String summary;

    private EventDateTime start;

    private EventDateTime end;
}
