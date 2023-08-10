package br.com.heliandro.utils;

import br.com.heliandro.exceptions.MathOperationException;

public class NumberConverter {

    public static Double convertToDouble(String strNumber) {
        if (strNumber == null)
            return null;

        String number = strNumber.replaceAll(",", ".");

        if (isNumeric(number))
            return Double.parseDouble(number);

        throw new MathOperationException("Invalid numeric value: " + strNumber);
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null)
            return false;

        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
