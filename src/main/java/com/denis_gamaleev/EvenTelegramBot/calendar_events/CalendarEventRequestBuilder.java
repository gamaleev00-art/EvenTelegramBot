package com.denis_gamaleev.EvenTelegramBot.calendar_events;

import com.denis_gamaleev.EvenTelegramBot.calendar_events.dto.CalendarEventRequest;
import com.denis_gamaleev.EvenTelegramBot.calendar_events.dto.EventDateTime;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class CalendarEventRequestBuilder {

    public static CalendarEventRequest build(String title,
                                             LocalDateTime start,
                                             LocalDateTime end,
                                             ZoneId zoneId) {

        CalendarEventRequest request = new CalendarEventRequest();
        request.setSummary(title);

        request.setStart(toEventDateTime(start, zoneId));
        request.setEnd(toEventDateTime(end, zoneId));

        return request;
    }

    private static EventDateTime toEventDateTime(LocalDateTime time,
                                                 ZoneId zoneId) {
        EventDateTime dt = new EventDateTime();
        dt.setDateTime(time.toString());
        dt.setTimeZone(zoneId.toString());
        return dt;
    }
}
