package com.denis_gamaleev.EvenTelegramBot.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthTokenResponse {

    private String access_token;
    private String refresh_token;
    private int expires_in;
    private String token_type;
}
