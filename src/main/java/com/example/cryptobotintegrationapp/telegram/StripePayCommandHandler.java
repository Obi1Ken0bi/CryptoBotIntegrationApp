package com.example.cryptobotintegrationapp.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.invoices.SendInvoice;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.payments.LabeledPrice;

import java.util.List;
@Component
public class StripePayCommandHandler implements CommandHandler{
    @Value("${telegram.payment.token}")
    private String paymentToken;
    @Override
    public String getCommand() {
        return "/stripe";
    }

    @Override
    public SendInvoice handleCommand(Message message) {
        SendInvoice sendInvoice = new SendInvoice();
        sendInvoice.setChatId(message.getChatId());
        sendInvoice.setCurrency("EUR");
        sendInvoice.setTitle("Test");
        sendInvoice.setDescription("test description");
        sendInvoice.setPayload(message.getChatId().toString());
        sendInvoice.setProviderToken(paymentToken);
        LabeledPrice labeledPrice = new LabeledPrice();
        labeledPrice.setLabel("test");
        labeledPrice.setAmount(145);
        sendInvoice.setPrices(List.of(labeledPrice));
        return sendInvoice;
    }
}
