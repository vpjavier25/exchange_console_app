package com.exchange.app.utils;

import com.exchange.app.models.CurrencyConversor;
import org.fusesource.jansi.Ansi;

import java.text.DecimalFormat;

public class Formatter {
    public static String formatNumber(double value) {

        if(value >=  10000000 || value <=  0.0001)
            return Double.toString(value);
        DecimalFormat df = new DecimalFormat("###,###,###,###,##0.#############");
        return df.format(value);
    }

    public static String formatConversionInfo(CurrencyConversor conversor) {
        String salto = "\n";

        return salto
                + Ansi.ansi().fgGreen().a("Información acerca de la conversión: ").reset().toString() + salto
                + "Moneda Base: " + conversor.getBaseCurrency() + salto
                + "Moneda Objetivo: " + conversor.getTargetCurrency() + salto
                + "Taza de conversión: " + conversor.getConversionRate() + salto
                + "Conversión: " + conversor.getConversionResult();

    }
}
