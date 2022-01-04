package com.taylorswiftcn.justwei.util;

import lombok.Getter;

import java.text.DecimalFormat;

@Getter
public enum UnitConvert {

    Thousand(1000, "千", "K"),
    TenThousand(10000, "万", "W"),
    Million(100000000, "百万", "M");

    private final double value;
    private final String symbol_cn;
    private final String symbol_en;

    UnitConvert(long value, String symbol_cn, String symbol_en) {
        this.value = value;
        this.symbol_cn = symbol_cn;
        this.symbol_en = symbol_en;
    }

    public static String formatCN(UnitConvert unitConvert, double value) {
        return formatCN(unitConvert, value, 0);
    }

    public static String formatEN(UnitConvert unitConvert, double value) {
        return formatEN(unitConvert, value, 0);
    }

    public static String formatCN(UnitConvert unitConvert, double value, int decimalPlace) {
        boolean negative = value < 0;
        value = Math.abs(value);

        if (value >= unitConvert.getValue()) {
            return (negative ? "-" : "") + getFormat(decimalPlace).format(value / unitConvert.getValue()) + unitConvert.getSymbol_cn();
        }

        return (negative ? "-" : "") + value + "";
    }

    public static String formatEN(UnitConvert unitConvert, double value, int decimalPlace) {
        boolean negative = value < 0;
        value = Math.abs(value);

        if (value >= unitConvert.getValue()) {
            return (negative ? "-" : "") + getFormat(decimalPlace).format(value / unitConvert.getValue()) + unitConvert.getSymbol_en();
        }

        return (negative ? "-" : "") + value + "";
    }

    private static DecimalFormat getFormat(int size) {
        StringBuilder sb = new StringBuilder("0");
        if (size > 0) {
            sb.append(".");
            for (int i = 0; i < size; i++) {
                sb.append("0");
            }
        }

        return new DecimalFormat(sb.toString());
    }
}
