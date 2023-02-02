package com.example.cryptobotintegrationapp.integration.cryptobot.controller;

import com.example.cryptobotintegrationapp.integration.cryptobot.data.WebhookUpdate;
import com.example.cryptobotintegrationapp.integration.cryptobot.pesristence.PaidInvoice;
import com.example.cryptobotintegrationapp.integration.cryptobot.pesristence.PaidInvoiceDao;
import com.example.cryptobotintegrationapp.telegram.ExampleShopBot;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/secret-webhook")
@RequiredArgsConstructor
public class WebHookController {
    @Value("${crypto-pay.api.token}")
    private String token;
    private final ExampleShopBot exampleShopBot;
    private final PaidInvoiceDao paidInvoiceDao;

    @PostMapping
    public void getUpdate(@RequestBody WebhookUpdate update,
                          @RequestHeader("crypto-pay-api-signature") String signature,
                          HttpEntity<String> httpEntity) {
        HashCode tokenHash = Hashing.sha256().hashString(token, StandardCharsets.UTF_8);
        String hash = Hashing.hmacSha256(tokenHash.asBytes()).hashString(httpEntity.getBody(), StandardCharsets.UTF_8)
                .toString();
        if (hash.equals(signature)) {
            String chatId = update.getPayload().getPayload();
            exampleShopBot.sendInvoicePaid(chatId, update.getPayload().getDescription());
            paidInvoiceDao.save(new PaidInvoice(update.getPayload().getInvoiceId().toString(), Long.parseLong(chatId)));

        }
    }
}
