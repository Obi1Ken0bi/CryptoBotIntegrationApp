package com.example.cryptobotintegrationapp.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ExampleShopBot extends TelegramLongPollingBot {
    @Value("${telegram.bot.token}")
    private String token;
    private final Map<String, CommandHandler> commandToHandler = new HashMap<>();

    public ExampleShopBot(List<CommandHandler> commandHandlers) {
        for (CommandHandler commandHandler : commandHandlers) {
            commandToHandler.put(commandHandler.getCommand(), commandHandler);
        }
    }


    @Override
    public String getBotUsername() {
        return "exampleCryptoShopBot";
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        handleMessage(message);
    }

    private void handleMessage(Message message) {
        String text = message.getText();
        String response = commandToHandler.get(text).handleCommand(message);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendInvoicePaid(String chatId, String description) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Your invoice " + description + " is paid");
        sendMessage.setChatId(chatId);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
