package com.denis_gamaleev.EvenTelegramBot.parsers;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Component
public class RussianDateTimeParser implements DateTimeParser {
    private static final Pattern DATE_PATTERN =
            Pattern.compile("(\\d{1,2})\\s+(января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря)");
    private static final Pattern TIME_PATTERN =
            Pattern.compile("в\\s*(\\d{1,2})(?::(\\d{2}))?");

    @Override
    public LocalDateTime parse(String text, ZoneId zoneId) {

        text = text.toLowerCase();
        LocalDate today = LocalDate.now(zoneId);

        if (text.contains("сегодня")) {
            return parseTime(text, today);
        }

        if (text.contains("завтра")) {
            return parseTime(text, today.plusDays(1));
        }
        if (text.contains("послезавтра")) {
            return parseTime(text, today.plusDays(2));
        }
        if (text.matches(".*\\d{1,2} .*")) {
            return parseExplicitDate(text, zoneId);
        }

        throw new IllegalArgumentException("Не удалось распознать дату");
    }

    private LocalDateTime parseExplicitDate(String text, ZoneId zoneId) {

        Matcher matcher = DATE_PATTERN.matcher(text);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Дата не найдена");
        }

        String dayMonth = matcher.group(1) + " " + matcher.group(2);

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("d MMMM", Locale.forLanguageTag("ru"));

        LocalDate date = LocalDate.parse(dayMonth, formatter)
                .withYear(LocalDate.now(zoneId).getYear());

        return parseTime(text, date);
    }

    private LocalDateTime parseTime(String text, LocalDate date) {

        if (text.contains("пол")) {
            int hour = extractHour(text) - 1;
            return date.atTime(hour, 30);
        }

        int hour = extractHour(text);
        int minute = extractMinute(text);

        return date.atTime(hour, minute);
    }

    private int extractHour(String text) {
        Matcher m = TIME_PATTERN.matcher(text);
        if (m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return 9;
    }

    private int extractMinute(String text) {
        Matcher m = TIME_PATTERN.matcher(text);
        if (m.find() && m.group(2) != null) {
            return Integer.parseInt(m.group(2));
        }
        return 0;
    }
}

