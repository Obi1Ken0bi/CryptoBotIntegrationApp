package com.example.cryptobotintegrationapp;

import com.example.cryptobotintegrationapp.telegram.ExampleShopBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class AppConfig {

    @Bean
    TelegramBotsApi telegramBotsApi(ExampleShopBot shopBot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(shopBot);
        return telegramBotsApi;
    }
}
