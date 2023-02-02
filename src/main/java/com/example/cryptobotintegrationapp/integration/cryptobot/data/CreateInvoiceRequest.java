package com.example.cryptobotintegrationapp.integration.cryptobot.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateInvoiceRequest {
    //Currency code. Supported assets: “BTC”, “TON”, “ETH”, “USDT”, “USDC” and “BUSD”.
    private Asset asset;
    //Amount of the invoice in float. For example: 125.50
    private String amount;
    //Optional. Description for the invoice. User will see this description when they pay the invoice. Up to 1024 characters.
    @Nullable
    private String description;
    //Optional. Text of the message that will be shown to a user after the invoice is paid. Up to 2o48 characters.
    @Nullable
    @JsonProperty("hidden_message")
    private String hiddenMessage;
    //    Optional. Name of the button that will be shown to a user after the invoice is paid.
//    Supported names:
//    viewItem – “View Item”
//    openChannel – “View Channel”
//    openBot – “Open Bot”
//    callback – “Return”
    @Nullable
    @JsonProperty("paid_btn_name")
    private String paidBtnName;
    //    Optional. Required if paid_btn_name is used. URL to be opened when the button is pressed. You can set any success link (for example, a link to your bot). Starts with https or http.
    @Nullable
    @JsonProperty("paid_btn_url")
    private String paidBtnUrl;
    //Optional. Any data you want to attach to the invoice (for example, user ID, payment ID, ect). Up to 4kb.
    @Nullable
    private String payload;
    //Optional. Allow a user to add a comment to the payment. Default is true.
    @Nullable
    @JsonProperty("allow_comments")
    private Boolean allowComments;
    //Optional. Allow a user to pay the invoice anonymously. Default is true.
    @Nullable
    @JsonProperty("allow_anonymous")
    private Boolean allowAnonymous;
    //Optional. You can set a payment time limit for the invoice in seconds. Values between 1-2678400 are accepted.
    @Nullable
    @JsonProperty("expires_in")
    private Long expiresIn;
}
