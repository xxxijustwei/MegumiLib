package com.taylorswiftcn.justwei.util;

import lombok.Getter;

import java.text.DecimalFormat;

@Getter
public enum UnitConvert {

    Thousand(1000, "千", "K"),
    TenThousand(10000, "万", "W"),
    Million(100000000, "百万", "M");

    private final long value;
    private final String symbol_cn;
    private final String symbol_en;

    UnitConvert(long value, String symbol_cn, String symbol_en) {
        this.value = value;
        this.symbol_cn = symbol_cn;
        this.symbol_en = symbol_en;
    }

    public final static DecimalFormat decimalFormat = new java.text.DecimalFormat("0");

    public static String formatCN(UnitConvert unitConvert, long value) {
        boolean negative = value < 0;
        value = Math.abs(value);

        if (value >= unitConvert.getValue()) {
            return (negative ? "-" : "") + decimalFormat.format((double) value / (double) unitConvert.getValue()) + unitConvert.getSymbol_cn();
        }

        return (negative ? "-" : "") + value + "";
    }

    public static String formatEN(UnitConvert unitConvert, long value) {
        boolean negative = value < 0;
        value = Math.abs(value);

        if (value >= unitConvert.getValue()) {
            return (negative ? "-" : "") + decimalFormat.format((double) value / (double) unitConvert.getValue()) + unitConvert.getSymbol_en();
        }

        return (negative ? "-" : "") + value + "";
    }
}
