package com.example.cryptobotintegrationapp.telegram;

import com.example.cryptobotintegrationapp.integration.cryptobot.pesristence.PaidInvoiceDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class PaidFunctionalityCommandHandler implements CommandHandler {
    private final PaidInvoiceDao paidInvoiceDao;

    @Override
    public String getCommand() {
        return "/paid";
    }

    @Override
    public SendMessage handleCommand(Message message) {
        String resp;
        if (paidInvoiceDao.hasAnyPaidInvoice(message.getChatId())) {
            resp = "you have paid access";
        }else {
            resp = "you dont have paid access";
        }
        return new SendMessage(message.getChatId().toString(), resp);
    }
}
