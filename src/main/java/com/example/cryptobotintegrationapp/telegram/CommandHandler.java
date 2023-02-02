package com.example.cryptobotintegrationapp.telegram;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandHandler {
    String getCommand();

    String handleCommand(Message message);
}
