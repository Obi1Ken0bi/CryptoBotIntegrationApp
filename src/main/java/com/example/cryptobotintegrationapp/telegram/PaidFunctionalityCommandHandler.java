package com.example.cryptobotintegrationapp.telegram;

import com.example.cryptobotintegrationapp.integration.cryptobot.CryptoBotClient;
import com.example.cryptobotintegrationapp.integration.cryptobot.data.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class PaidFunctionalityCommandHandler implements CommandHandler {
    private final CryptoBotClient cryptoBotClient;

    @Override
    public String getCommand() {
        return "/paid";
    }

    @Override
    public String handleCommand(Message message) {
        if (cryptoBotClient.getInvoices(null, null, null, null, null)
                .stream().filter(x -> x.getPayload().equals(message.getChatId().toString()))
                .anyMatch(x -> x.getStatus().equals(Status.PAID.toString()))) {
            return "you have paid access";
        }
        return "you dont have paid access";
    }
}
