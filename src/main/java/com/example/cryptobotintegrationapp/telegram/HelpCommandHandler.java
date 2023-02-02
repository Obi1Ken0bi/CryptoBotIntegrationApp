package com.example.cryptobotintegrationapp.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class HelpCommandHandler implements CommandHandler {
    @Override
    public String getCommand() {
        return "/help";
    }

    @Override
    public String handleCommand(Message message) {
        return "/pay gives us 0.01 TON\r\n/my lists your paid invoices\r\n/paid checks if you have access to paid functionality";
    }
}
