package com.denis_gamaleev.EvenTelegramBot.controller;

import com.denis_gamaleev.EvenTelegramBot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class TelegramController {
    private final UserService userService;
    @Autowired
    public TelegramController(UserService userService) {
        this.userService = userService;
    }


}
