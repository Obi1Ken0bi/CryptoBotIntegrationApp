package com.example.cryptobotintegrationapp.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class StartCommandHandler implements CommandHandler {
    @Override
    public String getCommand() {
        return "/start";
    }

    @Override
    public SendMessage handleCommand(Message message) {
        String text = "type /help for help";
        return new SendMessage(message.getChatId().toString(), text);
    }
}
