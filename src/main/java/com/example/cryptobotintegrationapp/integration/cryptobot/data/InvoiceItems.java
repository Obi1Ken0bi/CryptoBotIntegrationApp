package com.example.cryptobotintegrationapp.integration.cryptobot.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceItems {
    private List<Invoice> items;
}
