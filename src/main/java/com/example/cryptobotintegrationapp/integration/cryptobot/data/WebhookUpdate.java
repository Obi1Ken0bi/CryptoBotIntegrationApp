package com.example.cryptobotintegrationapp.integration.cryptobot.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebhookUpdate {
    private String updateId;
    private String updateType;
    private LocalDateTime requestDate;
    private Invoice payload;
}
