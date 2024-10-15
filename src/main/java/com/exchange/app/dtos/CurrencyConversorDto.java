package com.exchange.app.dtos;

public record CurrencyConversorDto(String base_code,
                                   String target_code,
                                   double conversion_rate,
                                   double conversion_result
                                   ) {
}
