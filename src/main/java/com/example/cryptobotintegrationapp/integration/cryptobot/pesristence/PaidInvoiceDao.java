package com.example.cryptobotintegrationapp.integration.cryptobot.pesristence;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaidInvoiceDao {
    public static final String INSERT_STATEMENT = "insert into paid_invoice values(:invoiceId, :chatId)";
    public static final String EXISTS_STATEMENT = "select exists(select 1 from paid_invoice where chat_id=:chatId) as \"exists\"";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void save(PaidInvoice paidInvoice) {
        jdbcTemplate.update(INSERT_STATEMENT,
                new MapSqlParameterSource()
                        .addValue("invoiceId", paidInvoice.getInvoiceId())
                        .addValue("chatId", paidInvoice.getChatId()));
    }

    public boolean hasAnyPaidInvoice(Long chatId) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(EXISTS_STATEMENT,
                new MapSqlParameterSource()
                        .addValue("chatId", chatId),
                Boolean.class
        ));
    }
}
