package com.example.cryptobotintegrationapp.telegram;

import com.example.cryptobotintegrationapp.integration.cryptobot.pesristence.PaidInvoice;
import com.example.cryptobotintegrationapp.integration.cryptobot.pesristence.PaidInvoiceDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerPreCheckoutQuery;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.payments.SuccessfulPayment;
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
    private final PaidInvoiceDao paidInvoiceDao;

    public ExampleShopBot(List<CommandHandler> commandHandlers, PaidInvoiceDao paidInvoiceDao) {
        this.paidInvoiceDao = paidInvoiceDao;
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
        if (message != null) {
            handleMessage(message);
        }
        if(update.getPreCheckoutQuery() != null) {
            handlePreCheckout(update);
        }
    }

    private void handlePreCheckout(Update update) {
        AnswerPreCheckoutQuery answer = new AnswerPreCheckoutQuery();
        answer.setPreCheckoutQueryId(update.getPreCheckoutQuery().getId());
        answer.setOk(true);
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleMessage(Message message) {
        String text = message.getText();
        if (text != null) {
            BotApiMethodMessage apiMethodMessage = commandToHandler.get(text).handleCommand(message);
            try {
                execute(apiMethodMessage);
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }
        SuccessfulPayment successfulPayment = message.getSuccessfulPayment();
        if(successfulPayment != null){
            handleSuccessfulPayment(message, successfulPayment);
        }
    }

    private void handleSuccessfulPayment(Message message, SuccessfulPayment successfulPayment) {
        String invId = successfulPayment.getTelegramPaymentChargeId();
        paidInvoiceDao.save(new PaidInvoice(invId, message.getChatId()));
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), "payment successful");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
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
