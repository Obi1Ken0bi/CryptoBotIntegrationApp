package com.example.cryptobotintegrationapp.telegram;

import com.example.cryptobotintegrationapp.integration.cryptobot.CryptoBotClient;
import com.example.cryptobotintegrationapp.integration.cryptobot.data.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MyInvoicesCommandHandler implements CommandHandler {
    private final CryptoBotClient cryptoBotClient;

    @Override
    public String getCommand() {
        return "/my";
    }

    @Override
    public String handleCommand(Message message) {
        return cryptoBotClient.getInvoices(null, null, null, null, null)
                .stream().filter(x -> x.getPayload().equals(message.getChatId().toString()))
                .filter(x -> x.getStatus().equals(Status.PAID.toString()))
                .map(x -> x.getAsset().toString() + ":" + x.getAmount() + "\r\n")
                .collect(Collectors.joining());
    }
}
