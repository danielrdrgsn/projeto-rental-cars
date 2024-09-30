package utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberFormatter {

    public static String valorBigDecimalToString(BigDecimal valor) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return df.format(valor);
    }

    public static BigDecimal valorStringToBigDecimal(String valor) {
        if (valor == null || valor.isEmpty()) {
            throw new IllegalArgumentException("O valor fornecido não pode ser nulo ou vazio.");
        }
        String valorFormatado = valor.replace(".", "").replace(",", ".");
        return new BigDecimal(valorFormatado);
    }
}
