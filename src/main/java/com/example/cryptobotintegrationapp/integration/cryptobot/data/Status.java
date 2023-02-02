package com.example.cryptobotintegrationapp.integration.cryptobot.data;

import java.util.Locale;

public enum Status {
    ACTIVE, PAID;

    @Override
    public String toString() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
