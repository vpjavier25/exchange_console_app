package com.exchange.app.models;

import com.exchange.app.dtos.CurrencyConversorDto;
import com.exchange.app.utils.Formatter;

public class CurrencyConversor {
    private String amount;
    private String baseCurrency;
    private String targetCurrency;
    private String conversionRate;
    private String conversionResult;
    private String conversionDate;

    public CurrencyConversor(){

    }

    public CurrencyConversor(String amount, String conversionDate, CurrencyConversorDto cc){
        this.amount = Formatter.formatNumber(Double.parseDouble(amount));
        this.conversionDate = conversionDate;
        this.baseCurrency = cc.base_code();
        this.targetCurrency = cc.target_code();
        this.conversionRate = Formatter.formatNumber(cc.conversion_rate());
        this.conversionResult = Formatter.formatNumber(cc.conversion_result());
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public String getConversionRate() {
        return conversionRate;
    }

    public String getConversionResult() {
        return conversionResult;
    }


    @Override
    public String toString() {
        return baseCurrency + " a " + targetCurrency
                + " | "+"Cantidad a convertir: "+ amount
                + " | " +"Tasa: "+ conversionRate
                + " | " +"Conversion: " +conversionResult
                + " | " + "Fecha de conversión: "+conversionDate;
    }
}
