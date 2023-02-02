package com.example.cryptobotintegrationapp.integration.cryptobot;

import com.example.cryptobotintegrationapp.integration.cryptobot.data.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CryptoBotClient {
    private final RestTemplate cryptoRestTemplate;

    public Invoice createInvoice(CreateInvoiceRequest request) {
        ResponseEntity<InvoiceApiResponse> response = cryptoRestTemplate.postForEntity("/createInvoice", request, InvoiceApiResponse.class);
        return response.getBody().getResult();
    }

    public List<Invoice> getInvoices(Asset asset, List<String> invoiceIds, Status status, Long offset, Long count) {
        Map<String, Object> urlParams = new HashMap<>();
        if (asset != null) {
            urlParams.put("asset", asset.toString());
        }
        if (invoiceIds != null && !invoiceIds.isEmpty()) {
            urlParams.put("invoice_ids", Strings.join(invoiceIds, ','));
        }
        if (status != null) {
            urlParams.put("status", status.toString());
        }
        if (offset != null) {
            urlParams.put("offset", offset);
        }
        if (count != null) {
            urlParams.put("count", count);
        }
        ResponseEntity<GetInvoicesApiResponse> response = cryptoRestTemplate.exchange("/getInvoices", HttpMethod.GET, null, GetInvoicesApiResponse.class, urlParams);
        return response.getBody().getResult().getItems();
    }
}
