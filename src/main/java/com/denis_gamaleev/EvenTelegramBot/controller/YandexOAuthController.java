package com.denis_gamaleev.EvenTelegramBot.controller;

import com.denis_gamaleev.EvenTelegramBot.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/yandex")
@RequiredArgsConstructor
public class YandexOAuthController {

    private final AuthService authService;

    @GetMapping("/callback")
    public String callback(
            @RequestParam String code,
            @RequestParam String state
    ) {
        authService.handleCallback(code, Long.parseLong(state));
        return "Авторизация успешна. Вернитесь в Telegram.";
    }
}
