package com.denis_gamaleev.EvenTelegramBot.calendar_events.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDateTime {
    private String dateTime;
    private String timeZone;
}
