package com.example.cryptobotintegrationapp.telegram;

import com.example.cryptobotintegrationapp.integration.cryptobot.pesristence.PaidInvoiceDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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
    public String handleCommand(Message message) {
        if (paidInvoiceDao.hasAnyPaidInvoice(message.getChatId())) {
            return "you have paid access";
        }
        return "you dont have paid access";
    }
}
