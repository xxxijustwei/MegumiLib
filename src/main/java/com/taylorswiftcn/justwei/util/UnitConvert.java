package com.taylorswiftcn.justwei.util;

import java.text.DecimalFormat;

public class UnitConvert {

    private static final long million = 100000000;
    private static final long tenThousand = 10000;
    public static DecimalFormat decimalFormat = new java.text.DecimalFormat("0.00");

    public static String getFormatLong(long input) {
        boolean negative = input < 0;
        input = Math.abs(input);

        if (input > million) {
            return (negative ? "-" : "") + decimalFormat.format((double) input / (double) million) + " m";
        } else if (input > tenThousand) {
            return (negative ? "-" : "") + decimalFormat.format((double) input / (double) tenThousand) + " w";
        } else {
            return (negative ? "-" : "") + input + "";
        }
    }
}
