package com.example.cryptobotintegrationapp.telegram;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandHandler {
    String getCommand();

    BotApiMethodMessage handleCommand(Message message);
}
