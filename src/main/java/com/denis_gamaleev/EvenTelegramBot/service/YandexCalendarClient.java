package com.denis_gamaleev.EvenTelegramBot.service;

import com.denis_gamaleev.EvenTelegramBot.calendar_events.dto.CalendarEventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class YandexCalendarClient {

    private final RestTemplate yandexApiRestTemplate;

    public void createEvent(Long telegramUserId, CalendarEventRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Telegram-User-Id", telegramUserId.toString());

        HttpEntity<CalendarEventRequest> entity =
                new HttpEntity<>(request, headers);

        yandexApiRestTemplate.postForObject(
                "https://api.calendar.yandex.ru/v3/events",
                entity,
                Void.class
        );
    }
}

