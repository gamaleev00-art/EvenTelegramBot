package com.denis_gamaleev.EvenTelegramBot.config;

import com.denis_gamaleev.EvenTelegramBot.service.YandexAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate plainRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate yandexApiRestTemplate(
            YandexAuthInterceptor interceptor
    ) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(interceptor);
        return restTemplate;
    }
}
