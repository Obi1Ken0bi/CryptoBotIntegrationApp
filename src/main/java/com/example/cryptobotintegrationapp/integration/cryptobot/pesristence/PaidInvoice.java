package com.example.cryptobotintegrationapp.integration.cryptobot.pesristence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaidInvoice {
    private Long invoiceId;
    private Long chatId;
}
