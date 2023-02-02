package com.example.cryptobotintegrationapp.telegram;

import com.example.cryptobotintegrationapp.integration.cryptobot.CryptoBotClient;
import com.example.cryptobotintegrationapp.integration.cryptobot.data.Asset;
import com.example.cryptobotintegrationapp.integration.cryptobot.data.CreateInvoiceRequest;
import com.example.cryptobotintegrationapp.integration.cryptobot.data.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class BuyCommandHandler implements CommandHandler {
    private final CryptoBotClient cryptoBotClient;

    @Override
    public String getCommand() {
        return "/pay";
    }

    @Override
    public SendMessage handleCommand(Message message) {
        Long chatId = message.getChatId();
        CreateInvoiceRequest request = new CreateInvoiceRequest(Asset.TON, "0.01", "test", null,
                null, null, chatId.toString(), false, false, null);
        Invoice createdInvoice = cryptoBotClient.createInvoice(request);
        String payUrl = createdInvoice.getPayUrl();
        return new SendMessage(message.getChatId().toString(), payUrl);
    }
}
