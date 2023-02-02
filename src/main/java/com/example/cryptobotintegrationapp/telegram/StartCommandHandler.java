package com.example.cryptobotintegrationapp.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class StartCommandHandler implements CommandHandler {
    @Override
    public String getCommand() {
        return "/start";
    }

    @Override
    public String handleCommand(Message message) {
        return "type /help for help";
    }
}
